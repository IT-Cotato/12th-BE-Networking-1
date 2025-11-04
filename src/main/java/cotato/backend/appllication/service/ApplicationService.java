package cotato.backend.appllication.service;

import cotato.backend.applicant.entity.Applicant;
import cotato.backend.applicant.repository.ApplicantRepository;
import cotato.backend.appllication.dto.ApplicationDetailResponseDto;
import cotato.backend.appllication.dto.ApplicationListResponseDto;
import cotato.backend.appllication.dto.ApplicationCreateRequestDto;
import cotato.backend.appllication.entity.Application;
import cotato.backend.appllication.repository.ApplicationRepository;
import cotato.backend.appllication.enums.Part;
import cotato.backend.likes.entity.Likes;
import cotato.backend.likes.repository.LikesRepository;
import cotato.backend.manager.entity.Manager;
import cotato.backend.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;
    private final ManagerRepository managerRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public void createApplication(ApplicationCreateRequestDto request) {
        validateRequest(request);

        Applicant applicant = Applicant.builder()
                .name(request.getName())
                .birthYear(request.getBirthYear())
                .phoneNumber(request.getPhoneNumber())
                .build();
        applicantRepository.save(applicant);

        Application app = Application.builder()
                .applicant(applicant)
                .name(request.getName())
                .generation(request.getGeneration())
                .birthYear(request.getBirthYear())
                .part(Part.fromString(request.getPart())) // 문자열 → Enum 변환
                .ability(request.getAbility())
                .passion(request.getPassion())
                .phoneNumber(request.getPhoneNumber())
                .applicationTime(request.getApplicationTime())
                .likeCount(0L)
                .build();

        applicationRepository.save(app);
    }

    /**
     * 지원서 단건 조회
     */
    public ApplicationDetailResponseDto getApplication(Long id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지원서입니다."));

        return ApplicationDetailResponseDto.builder()
                .name(app.getName())
                .generation(app.getGeneration())
                .birthYear(app.getBirthYear())
                .part(app.getPart().getKorean()) // Enum → String 변환
                .ability(app.getAbility())
                .passion(app.getPassion())
                .phoneNumber(app.getPhoneNumber())
                .applicationTime(app.getApplicationTime())
                .build();
    }

    /**
     * 지원서 목록 조회 (필터 기준)
     */
    public List<ApplicationListResponseDto> getApplications(String filterType, Integer period) {
        List<Application> list;
        PageRequest page = PageRequest.of(0, 10);

        switch (filterType) {
            case "generation":
                list = applicationRepository.findByGenerationOrderByIdDesc(period, page);
                break;
            case "likes":
                list = applicationRepository.findAllByOrderByLikeCountDesc(page);
                break;
            case "mixed":
                list = applicationRepository.findByGenerationOrderByLikeCountDesc(period, page);
                break;
            default:
                throw new IllegalArgumentException("잘못된 필터 기준입니다. (generation / likes / mixed 중 하나)");
        }

        return list.stream()
                .map(app -> ApplicationListResponseDto.builder()
                        .name(app.getName())
                        .generation(app.getGeneration())
                        .part(app.getPart().getKorean()) // Enum → String 변환
                        .likeCount(app.getLikeCount())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 유효성 검증
     */
    private void validateRequest(ApplicationCreateRequestDto req) {
        if (req.getName().length() < 2 || req.getName().length() > 10)
            throw new IllegalArgumentException("이름은 2~10자여야 합니다.");
        if (req.getGeneration() < 1)
            throw new IllegalArgumentException("기수는 1 이상이어야 합니다.");
        if (LocalDate.now().getYear() - req.getBirthYear() + 1 < 22 || LocalDate.now().getYear() - req.getBirthYear() + 1 > 30) // 현재 년도 - 출생년도 + 1 로 나이 계산
            throw new IllegalArgumentException("나이는 22~30세여야 합니다.");
        if (!List.of("기획", "디자이너", "프론트엔드", "백엔드", "PM", "DESIGN", "FE", "BE")
                .contains(req.getPart()))
            throw new IllegalArgumentException("유효하지 않은 파트입니다.");
        if (req.getAbility() < 0 || req.getAbility() > 10)
            throw new IllegalArgumentException("실력은 0~10 사이여야 합니다.");
        if (req.getPassion() < 0 || req.getPassion() > 10)
            throw new IllegalArgumentException("열정은 0~10 사이여야 합니다.");
        if (!req.getPhoneNumber().matches("^010\\d{8}$"))
            throw new IllegalArgumentException("휴대폰 번호는 010으로 시작하는 11자리여야 합니다.");
    }
}
