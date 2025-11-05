package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dto.response.ApplicantResponse;
import cotato.backend.domain.example.entity.Applicant;
import cotato.backend.domain.example.dao.ApplicantRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    // 지원자 상세 정보 조회
    public ApplicantResponse getApplicantDetail(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICANT_NOT_FOUND));

        return ApplicantResponse.of(applicant);
    }
}
