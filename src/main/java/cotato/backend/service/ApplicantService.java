package cotato.backend.service;

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

    // 지원자 정보 조회
    public ApplicantResponse getApplicant(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("지원자를 찾을 수 없습니다."));
        return new ApplicantResponse(applicant);
    }

    // 지원자 정보 수정
    @Transactional
    public void updateApplicant(Long id, ApplicantRequest request) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("지원자를 찾을 수 없습니다."));

        applicant.update(request.getName(), request.getAge(), request.getPhoneNumber());
    }

}
