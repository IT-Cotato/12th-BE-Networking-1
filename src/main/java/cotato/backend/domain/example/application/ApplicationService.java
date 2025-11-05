package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicationRepository;
import cotato.backend.domain.example.dto.request.ApplicationRequest;
import cotato.backend.domain.example.entity.Applicant;
import cotato.backend.domain.example.entity.Application;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantService applicantService;

    public enum FilterBy {
        likes, gisu, gisu_likes
    }

    @Transactional
    // 지원서를 생성
    // ApplicantService.upsert를 호출해 지원자 등록하며 ID를 받은 뒤, 
    // ApplicantService.getById를 호출해 지원자 정보를 가져옴
    public Long createApplication(ApplicationRequest request) {
        validateRequest(request);

        Long applicantId = applicantService.upsert(
                request.getName(),
                request.getAge(),
                request.getPhoneNumber()
        );
        Applicant applicant = applicantService.getById(applicantId);

        LocalDateTime appliedAt = request.getApplicationTime() != null ? request.getApplicationTime() : LocalDateTime.now();

        Application application = Application.builder()
                .name(request.getName())
                .period(request.getPeriod())
                .age(request.getAge())
                .part(request.getPart())
                .ability(request.getAbility())
                .passion(request.getPassion())
                .phoneNumber(request.getPhoneNumber())
                .applicationTime(appliedAt)
                .likeCount(0)
                .applicant(applicant)
                .build();

        return applicationRepository.save(application).getId();
    }

    // 지원서를 ID로 조회, 없으면 NOT_FOUND 예외를 던짐
    public Application getById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
    }

    // 지원서 리스트 조회, 필터(좋아요/기수/기수+좋아요)와 페이지 정보를 적용
    public List<Application> listApplications(FilterBy filterBy, Integer period, int page, int pageSize) {
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize);
        return switch (filterBy) {
            case likes -> applicationRepository.findTopByLikes(pageable);
            case gisu -> applicationRepository.findByPeriodOrderByApplicationTimeDesc(period, pageable);
            case gisu_likes -> applicationRepository.findByPeriodOrderByLikesDesc(period, pageable);
        };
    }

    // 지원서 입력값 유효성 검사
    private void validateRequest(ApplicationRequest request) {
        if (request.getName() == null || request.getName().length() < 2 || request.getName().length() > 10) {
            throw new IllegalArgumentException("이름은 한글 2글자 이상 10글자 이하여야 합니다.");
        }
        if (request.getPeriod() < 1) {
            throw new IllegalArgumentException("지원 기수는 1 이상의 정수여야 합니다.");
        }
        if (request.getAge() < 22 || request.getAge() > 30) {
            throw new IllegalArgumentException("나이는 22 이상 30 이하여야 합니다.");
        }
        if (request.getAbility() < 0 || request.getAbility() > 10) {
            throw new IllegalArgumentException("실력은 0 이상 10 이하여야 합니다.");
        }
        if (request.getPassion() < 0 || request.getPassion() > 10) {
            throw new IllegalArgumentException("열정은 0 이상 10 이하여야 합니다.");
        }
        if (request.getPhoneNumber() == null || !request.getPhoneNumber().matches("010\\d{8}")) {
            throw new IllegalArgumentException("휴대폰 번호는 010으로 시작하는 11자리여야 합니다.");
        }
        if (request.getPart() == null || !(request.getPart().equals("기획") || request.getPart().equals("디자이너") || request.getPart().equals("프론트엔드") || request.getPart().equals("백엔드"))) {
            throw new IllegalArgumentException("파트는 기획/디자이너/프론트엔드/백엔드 중 하나여야 합니다.");
        }
    }
}


