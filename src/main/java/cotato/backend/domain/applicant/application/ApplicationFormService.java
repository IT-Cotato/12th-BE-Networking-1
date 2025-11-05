package cotato.backend.domain.applicant.application;

import cotato.backend.common.enums.SortType;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicationFormRepository;
import cotato.backend.domain.applicant.dto.request.ApplicationFormRequest;
import cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse;
import cotato.backend.domain.applicant.dto.response.ApplicationFormResponse;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.entity.ApplicationForm;
import cotato.backend.domain.applicant.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationFormService {

    private final ApplicationFormRepository applicationFormRepository;
    private final ApplicantService applicantService;

    @Transactional
    public Long submit(ApplicationFormRequest request) {
        validateApplicationForm(request);

        Applicant applicant = applicantService.save(request.applicant());

        ApplicationForm applicationForm = ApplicationForm.builder()
                .applicant(applicant)
                .generation(request.generation())
                .part(request.part())
                .skillLevel(request.skillLevel())
                .passion(request.passion()).build();

        return applicationFormRepository.save(applicationForm).getId();
    }

    public ApplicationFormResponse findById(Long id) {
        return ApplicationFormResponse.from(
                applicationFormRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND))
        );
    }

    @Transactional
    public void updateStatus(Long id, Status status) {
        ApplicationForm applicationForm = applicationFormRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        applicationForm.updateStatus(status);
    }

    private void validateApplicationForm(ApplicationFormRequest request) {
        if (request.applicant() == null) {
            throw new AppException(ErrorCode.APPLICANT_REQUIRED);
        }
        if (request.generation() == null) {
            throw new AppException(ErrorCode.APPLICATION_FORM_GENERATION_REQUIRED);
        }
        if (request.part() == null) {
            throw new AppException(ErrorCode.APPLICATION_FORM_PART_REQUIRED);
        }
        if (request.skillLevel() == null) {
            throw new AppException(ErrorCode.APPLICATION_FORM_SKILL_LEVEL_REQUIRED);
        }
        if (request.passion() == null) {
            throw new AppException(ErrorCode.APPLICATION_FORM_PASSION_REQUIRED);
        }

        if (request.generation() < 1) {
            throw new AppException(ErrorCode.APPLICATION_FORM_INVALID_GENERATION);
        }
        if (request.skillLevel() < 0 || request.skillLevel() > 10) {
            throw new AppException(ErrorCode.APPLICATION_FORM_INVALID_SKILL_LEVEL);
        }
        if (request.passion() < 0 || request.passion() > 10) {
            throw new AppException(ErrorCode.APPLICATION_FORM_INVALID_PASSION);
        }
    }

    public Page<ApplicationFormListResponse> findByGeneration(Integer generation, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return applicationFormRepository.findByGenerationWithLikeCount(generation, pageable);
    }

    public Page<ApplicationFormListResponse> findBySort(SortType sortType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return switch (sortType) {
            case LIKES -> applicationFormRepository.findAllOrderByLikes(pageable);
            case LATEST -> applicationFormRepository.findAllOrderBySubmittedAtDesc(pageable);
            case OLDEST -> applicationFormRepository.findAllOrderBySubmittedAtAsc(pageable);
        };
    }

    public Page<ApplicationFormListResponse> findByGenerationAndSort(
            Integer generation, SortType sortType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return switch (sortType) {
            case LIKES -> applicationFormRepository.findByGenerationOrderByLikes(generation, pageable);
            case LATEST -> applicationFormRepository.findByGenerationOrderBySubmittedAtDesc(generation, pageable);
            case OLDEST -> applicationFormRepository.findByGenerationOrderBySubmittedAtAsc(generation, pageable);
        };
    }

    public ApplicationFormResponse findByApplicantInfo(String name, String phoneNum, Integer generation) {
        ApplicationForm applicationForm = applicationFormRepository.findByApplicantNameAndApplicantPhoneNumAndGeneration(name, phoneNum, generation);

        if (applicationForm == null) {
            throw new AppException(ErrorCode.APPLICATION_FORM_NOT_FOUND);
        }

        return ApplicationFormResponse.from(applicationForm);
    }
}
