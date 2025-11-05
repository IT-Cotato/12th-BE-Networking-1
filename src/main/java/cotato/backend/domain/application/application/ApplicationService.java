package cotato.backend.domain.application.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.dto.request.ApplicationRequest;
import cotato.backend.domain.application.dto.response.ApplicationDetailResponse;
import cotato.backend.domain.application.dto.response.ApplicationListResponse;
import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.like.dao.LikeRepository;
import cotato.backend.domain.like.entity.LikeEntity;
import cotato.backend.domain.staff.dao.StaffRepository;
import cotato.backend.domain.staff.entity.StaffEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<ApplicationListResponse> getApplicationList(String filterBy, Integer period, int page, int pageSize) {
        Page<ApplicationEntity> pageOfEntities;
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        // 전체 리스트 조회
        if (filterBy == null || filterBy.isBlank()) {
            pageOfEntities = applicationRepository.findAllWithApplicant(pageable);
        } else {
            // 조건에 따라 필터링
            switch (filterBy) {
                case "gisu" :
                    if (period == null) throw new AppException(ErrorCode.INVALID_PARAMETER);
                    pageOfEntities = applicationRepository.findByPeriod(period, pageable);
                    break;
                case "likes" :
                    pageOfEntities = applicationRepository.findAllOrderByLikeCountDesc(pageable);
                    break;
                case "gisu_likes" :
                    if (period == null) throw new AppException(ErrorCode.INVALID_PARAMETER);
                    pageOfEntities = applicationRepository.findByPeriodOrderByLikeCountDesc(period, pageable);
                    break;
                default:
                    throw new AppException(ErrorCode.INVALID_FILTER_BY);
            }
        }
        return pageOfEntities.map(ApplicationListResponse::from);
    }

    @Transactional
    public void likeApplication(Long applicationId, Long staffId) {
        ApplicationEntity application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));

        StaffEntity staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STAFF_NOT_FOUND));

        // 좋아요 증복 확인
        if (likeRepository.existsByApplicationAndStaff(application, staff)) {
            throw new AppException(ErrorCode.ALREADY_LIKED_APPLICATION);
        }

        LikeEntity newLike = LikeEntity.builder()
                .application(application)
                .staff(staff)
                .build();
        likeRepository.save(newLike);
        application.incrementLikeCount();
    }
    
}