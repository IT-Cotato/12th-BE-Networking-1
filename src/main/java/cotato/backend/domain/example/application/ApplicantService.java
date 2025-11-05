package cotato.backend.domain.example.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.dto.response.ApplicantResponse;
import cotato.backend.domain.example.entity.Applicant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    public Long create(ApplicantRequest req) {

        if (applicantRepository.existsByPhoneNumber(req.getPhoneNumber())) {
            throw new AppException(ErrorCode.INVALID_PARAMETER);
        }

        Applicant applicant = Applicant.builder()
                .name(req.getName())
                .age(req.getAge())
                .phoneNumber(req.getPhoneNumber())
                .build();

        return applicantRepository.save(applicant).getId();
    }

    @Transactional
    public ApplicantResponse getById(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        return mapToResponse(applicant);
    }

    @Transactional
    public ApplicantResponse update(Long id, ApplicantRequest req) {

        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        applicant.updateEntity(req.getName(), req.getAge(), req.getPhoneNumber());

        return mapToResponse(applicantRepository.save(applicant));
    }

    private ApplicantResponse mapToResponse(Applicant applicant) {
        return ApplicantResponse.builder()
                .name(applicant.getName())
                .age(applicant.getAge())
                .phoneNumber(applicant.getPhoneNumber())
                .build();
    }
}
