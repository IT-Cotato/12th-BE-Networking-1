package cotato.backend.domain.applicant.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.entity.Applicant;
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
    public Applicant save(ApplicantRequest request) {

        validateApplicant(request);

        return applicantRepository.findByPhoneNum(request.getPhoneNum())
                .orElseGet(() -> {
                    Applicant applicant = Applicant.builder()
                            .name(request.getName())
                            .age(request.getAge())
                            .phoneNum(request.getPhoneNum())
                            .build();
                    return applicantRepository.save(applicant);
                });
    }

    private void validateApplicant(ApplicantRequest request) {
        String name = request.getName();
        Integer age = request.getAge();

        // null 체크
        if (name == null || age == null) {
            throw new AppException(ErrorCode.INVALID_PARAMETER);
        }

        // 이름 길이 검증
        if (name.length() <2 || name.length() > 10) {
            throw new AppException(ErrorCode.INVALID_NAME_LENGTH);
        }

        // 나이 범위 검증
        if (age < 22 || age > 30) {
            throw new AppException(ErrorCode.INVALID_AGE_RANGE);
        }
    }

    public ApplicantResponse findById(Long id) {
        return ApplicantResponse.from(
                applicantRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND)));
    }

    @Transactional
    public ApplicantResponse update(Long id, ApplicantRequest request) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        validateApplicant(request);

        applicant.update(request.getName(), request.getAge(), request.getPhoneNum());

        return ApplicantResponse.from(applicant);
    }
}
