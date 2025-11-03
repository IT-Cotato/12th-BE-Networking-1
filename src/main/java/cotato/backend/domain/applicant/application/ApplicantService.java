package cotato.backend.domain.applicant.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantService {

	private final ApplicantRepository applicantRepository;

	@Transactional
	public ApplicantResponse getApplicant(Long id) {
        return ApplicantResponse.from(
                applicantRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICANT_NOT_FOUND))
        );
	}

    @Transactional
    public ApplicantResponse updateApplicant(Long id, ApplicantRequest request) {
        ApplicantEntity applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICANT_NOT_FOUND));
        applicant.update(
                request.name(),
                request.age(),
                request.phoneNumber()
        );
        return ApplicantResponse.from(applicant);
    }


}