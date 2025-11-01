package cotato.backend.applicant.service;

import cotato.backend.applicant.dto.ApplicantResponse;
import cotato.backend.applicant.dto.ApplicantUpdateRequest;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    /*
     * 지원자 정보 조회
     */
    @Transactional(readOnly = true)
    public ApplicantResponse getApplicant(Long applicantId){
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICANT_NOT_FOUND));

        return ApplicantResponse.from(applicant);
    }

    /*
     * 지원자 정보 수정
     */
    @Transactional
    public ApplicantResponse updateApplicant(Long applicantId, ApplicantUpdateRequest request){
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICANT_NOT_FOUND));

        applicant.updateInfo(
                request.getName(),
                request.getAge(),
                request.getPhoneNumber()
        );

        return ApplicantResponse.from(applicant);
    }
}
