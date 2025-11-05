package cotato.backend.domain.applicant.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicationFormRepository;
import cotato.backend.domain.applicant.dto.request.ApplicationFormRequest;
import cotato.backend.domain.applicant.dto.response.ApplicationFormResponse;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.entity.ApplicationForm;
import cotato.backend.domain.applicant.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
