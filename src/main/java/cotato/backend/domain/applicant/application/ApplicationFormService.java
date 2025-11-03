package cotato.backend.domain.applicant.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicationFormRepository;
import cotato.backend.domain.applicant.dto.request.ApplicationFormRequest;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.entity.ApplicationForm;
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

        Applicant applicant = applicantService.save(request.applicant());

        ApplicationForm applicationForm = ApplicationForm.builder()
                .applicant(applicant)
                .generation(request.generation())
                .part(request.part())
                .skillLevel(request.skillLevel())
                .passion(request.passion()).build();

        return applicationFormRepository.save(applicationForm).getId();
    }
}
