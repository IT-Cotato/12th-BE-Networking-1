package cotato.backend.domain.application.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.dto.request.ApplicationRequest;
import cotato.backend.domain.application.dto.response.ApplicationDetailResponse;
import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.like.dao.LikeRepository;
import cotato.backend.domain.staff.dao.StaffRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationService {

    private final ApplicantRepository applicantRepository;
    private final ApplicationRepository applicationRepository;
    private final StaffRepository staffRepository;
    private final LikeRepository likeRepository;

    @Transactional
	public Long createApplication(ApplicationRequest request) {
        // 휴대폰번호로 기존 지원자 찾고 없으면 생성
        ApplicantEntity applicant = applicantRepository
                .findByPhoneNumber(request.phoneNumber())
                .map(existingApplicant -> {
                    existingApplicant.update(request.name(), request.age(), request.phoneNumber());
                    return existingApplicant;
                })
                .orElseGet(() -> {
                    ApplicantEntity newApplicant = request.toApplicantEntity();
                    return applicantRepository.save(newApplicant);
                });

        ApplicationEntity application = request.toApplicationEntity(applicant);
        return applicationRepository.save(application).getId();
	}

    public ApplicationDetailResponse getApplicationDetail(Long id) {
        ApplicationEntity application = applicationRepository.findByIdWithApplicant(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        return ApplicationDetailResponse.from(application);
    }


}