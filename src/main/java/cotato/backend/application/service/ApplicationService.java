package cotato.backend.application.service;

import cotato.backend.application.dto.ApplicationCreateRequest;
import cotato.backend.application.dto.ApplicationDetailResponse;
import cotato.backend.application.dto.ApplicationListRequest;
import cotato.backend.application.dto.ApplicationListResponse;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.common.exception.ValidationException;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.repository.ApplicantRepository;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;

    // 서류 상세 조회
    @Transactional
    public ApplicationDetailResponse getApplicationDetail(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));
        return ApplicationDetailResponse.from(application);
    }

    // 서류 리스트 조회 (필터링)
    @Transactional
    public List<ApplicationListResponse> getApplicationList(ApplicationListRequest request) {
        Pageable pageable = PageRequest.of(
                request.getPage() - 1,
                request.getPageSize()
        );

        Page<Application> applications = switch (request.getFilterBy()) {
            case "likes" -> applicationRepository.findAllByOrderByLikeCountDesc(pageable);
            case "period" -> applicationRepository.findByPeriod(request.getPeriod(), pageable);
            case "period+likes" -> applicationRepository.findByPeriodOrderByLikeCountDesc(request.getPeriod(), pageable);
            default -> throw new ValidationException(ErrorCode.INVALID_PARAMETER);
        };

        return applications.stream()
                .map(ApplicationListResponse::from)
                .toList();
    }



    // 서류 생성
    @Transactional
    public ApplicationDetailResponse createApplication(ApplicationCreateRequest request){
        validateApplicationRequest(request);

        Applicant applicant = applicantRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> createApplicant(request));

        Application application = Application.builder()
                .applicant(applicant)
                .period(request.getPeriod())
                .part(request.getPart())
                .ability(request.getAbility())
                .passion(request.getPassion())
                .applicationTime(request.getApplicationTime())
                .build();

        Application savedApplication = applicationRepository.save(application);
        return ApplicationDetailResponse.from(savedApplication);
    }


    private void validateApplicationRequest(ApplicationCreateRequest request) {
        if (request.getName() == null || request.getName().length() < 2 || request.getName().length() > 10) {
            throw new ValidationException(ErrorCode.INVALID_NAME_LENGTH);
        }

        if (request.getAge() == null || request.getAge() < 22 || request.getAge() > 30) {
            throw new ValidationException(ErrorCode.INVALID_AGE_RANGE);
        }

        if (request.getPhoneNumber() == null || !request.getPhoneNumber().matches("^010\\d{8}$")) {
            throw new ValidationException(ErrorCode.INVALID_PHONE_NUMBER);
        }

        if (!isValidPart(request.getPart())) {
            throw new ValidationException(ErrorCode.INVALID_PART);
        }

        if (request.getPeriod() == null || request.getPeriod() < 1) {
            throw new ValidationException(ErrorCode.INVALID_PERIOD);
        }

        if (request.getAbility() == null || request.getAbility() < 0 || request.getAbility() > 10) {
            throw new ValidationException(ErrorCode.INVALID_SCORE_RANGE);
        }

        if (request.getPassion() == null || request.getPassion() < 0 || request.getPassion() > 10) {
            throw new ValidationException(ErrorCode.INVALID_SCORE_RANGE);
        }
    }

    private boolean isValidPart(String part) {
        return part != null &&
                (part.equals("기획") || part.equals("디자이너") ||
                        part.equals("프론트엔드") || part.equals("백엔드"));
    }


    private Applicant createApplicant(ApplicationCreateRequest request) {
        Applicant applicant = Applicant.builder()
                .name(request.getName())
                .age(request.getAge())
                .phoneNumber(request.getPhoneNumber())
                .build();
        return applicantRepository.save(applicant);
    }
}
