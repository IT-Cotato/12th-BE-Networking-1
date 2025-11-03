package cotato.backend.domain.applicant.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.entity.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public Long save(ApplicantRequest request) {

        validateApplicant(request);

        return applicantRepository.findByPhoneNum(request.getPhoneNum())
                .map(Applicant::getId)
                .orElseGet(() -> {
                    Applicant applicant = Applicant.builder()
                            .name(request.getName())
                            .age(request.getAge())
                            .phoneNum(request.getPhoneNum())
                            .build();
                    return applicantRepository.save(applicant).getId();
                });
    }

    private void validateApplicant(ApplicantRequest request) {
        String name = request.getName();
        Integer age = request.getAge();

        // 이름 길이 검증
        if (name.length() <2 || name.length() > 10) {
            throw new AppException(ErrorCode.INVALID_NAME_LENGTH);
        }

        // 나이 범위 검증
        if (age < 22 || age > 30) {
            throw new AppException(ErrorCode.INVALID_AGE_RANGE);
        }
    }
}
