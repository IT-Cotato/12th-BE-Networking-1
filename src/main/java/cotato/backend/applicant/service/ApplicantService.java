package cotato.backend.applicant.service;

import cotato.backend.applicant.dto.ApplicantCreateResponseDto;
import cotato.backend.applicant.dto.ApplicantUpdateRequestDto;
import cotato.backend.applicant.entity.Applicant;
import cotato.backend.applicant.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    /**
     * 지원자 단건 조회
     */
    public ApplicantCreateResponseDto getApplicant(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지원자입니다."));

        return ApplicantCreateResponseDto.builder()
                .id(applicant.getId())
                .name(applicant.getName())
                .phoneNumber(applicant.getPhoneNumber())
                .birthYear(applicant.getBirthYear())
                .build();
    }

    /**
     * 전체 지원자 조회
     */
    public List<ApplicantCreateResponseDto> getAllApplicants() {
        return applicantRepository.findAll().stream()
                .map(applicant -> ApplicantCreateResponseDto.builder()
                        .id(applicant.getId())
                        .name(applicant.getName())
                        .phoneNumber(applicant.getPhoneNumber())
                        .birthYear(applicant.getBirthYear())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 지원자 정보 수정
     */
    @Transactional
    public void updateApplicant(Long id, ApplicantUpdateRequestDto request) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지원자입니다."));

        applicant.update(request.getName(), request.getBirthYear());
    }

    /**
     * 지원자 삭제
     */
    @Transactional
    public void deleteApplicant(Long id) {
        if (!applicantRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 지원자입니다.");
        }
        applicantRepository.deleteById(id);
    }
}
