package cotato.backend.service;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.Applicant;
import cotato.backend.dto.request.ApplicantRequest;
import cotato.backend.dto.response.ApplicantResponse;
import cotato.backend.repository.ApplicantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    // GET /api/applicant/{applicantId}
    public ApplicantResponse getApplicant(Long applicnatId) {
        Applicant applicant = applicantRepository.findById(applicnatId)
                .orElseThrow(() -> new AppException(ErrorCode.APPLICANT_NOT_FOUND));
        return new ApplicantResponse(applicant);
    }

    // PUT /api/applicant/{applicantId}
    @Transactional
    public void updateApplicant(Long id, ApplicantRequest request) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPLICANT_NOT_FOUND));

        applicant.update(request.getName(), request.getAge(), request.getPhoneNumber());
    }

}
