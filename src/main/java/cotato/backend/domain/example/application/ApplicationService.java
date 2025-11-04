package cotato.backend.domain.example.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dto.response.ApplicationDetailResponse;
import cotato.backend.domain.example.dto.response.ApplicationListResponse;
import cotato.backend.domain.example.dto.request.ApplicationRegisterRequest;
import cotato.backend.domain.example.entity.Applicant;
import cotato.backend.domain.example.entity.Application;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.dao.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {
    private final ApplicantRepository applicantRepository;
    private final ApplicationRepository applicationRepository;

    // 지원서 등록
    @Transactional
    public Long registerApplication(ApplicationRegisterRequest request) {
        // 폰번호로 이미 지원자목록에 잇는지 확인해보고 없으면 새로 생성 후 반환
        Applicant applicant = applicantRepository.findByPhoneNumber(request.getPhoneNumber())
                .map(existingApplicant -> {
                    // 이미 존재할 경우 -> 새 지원서의 정보로 지원자 정보 수정
                    existingApplicant.setName(request.getName());
                    existingApplicant.setAge(request.getAge());
                    return existingApplicant;
                })
                .orElseGet(() -> {
                    // 새 지원자 생성
                    Applicant newApplicant = new Applicant();
                    newApplicant.setName(request.getName());
                    newApplicant.setAge(request.getAge());
                    newApplicant.setPhoneNumber(request.getPhoneNumber());
                    return applicantRepository.save(newApplicant);
                });

        boolean alreadySubmitted = applicationRepository.findByApplicantAndPeriod(applicant, request.getPeriod()).isPresent();

        if (alreadySubmitted) {
            // 이미 해당 기수에 지원서가 있다면 중복 제출 안됨
            throw new AppException(ErrorCode.APPLICATION_ALREADY_EXISTS);
        }

        Application application = createApplicationEntity(request, applicant);
        applicationRepository.save(application);
        return application.getApplicationId();
    }

    private Application createApplicationEntity(ApplicationRegisterRequest request, Applicant applicant) {
        Application application = new Application();
        application.setApplicant(applicant);
        application.setPeriod(request.getPeriod());
        application.setPart(Application.Part.fromKoreanName(request.getPart())); // 파트명 한글
        application.setAbility(request.getAbility());
        application.setPassion(request.getPassion());
        application.setApplicationTime(request.getApplicationTime());
        application.setLikesCount(0);
        return application;
    }

    // 지원서 id로 조회
    public ApplicationDetailResponse getApplicationDetail(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));

        return ApplicationDetailResponse.of(application);
    }

    public List<ApplicationListResponse> getApplicationList(String filterBy, int page, int pageSize) {

        Sort sort;
        if ("likes".equalsIgnoreCase(filterBy)) {
            // 좋아요 많은 순서 (likesCount 내림차순)
            sort = Sort.by(Sort.Direction.DESC, "likesCount");

        } else if ("gisu".equalsIgnoreCase(filterBy)) {
            // 기수별 (period 오름차순)
            sort = Sort.by(Sort.Direction.ASC, "period");

        } else if ("gisu+likes".equalsIgnoreCase(filterBy)) {
            // 기수 오름차순 + 좋아요 내림차순
            sort = Sort.by(
                    Sort.Order.asc("period"),
                    Sort.Order.desc("likesCount")
            );
        } else {
            // 필터링 기준이 유효하지 않을 경우 기본 -> period 오름차순
            sort = Sort.by(Sort.Direction.ASC, "period");
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

        Page<Application> applicationPage = applicationRepository.findAll(pageable);

        return applicationPage.getContent().stream()
                .map(ApplicationListResponse::of)
                .toList();
    }
}
