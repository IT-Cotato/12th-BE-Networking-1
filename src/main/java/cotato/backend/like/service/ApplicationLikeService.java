package cotato.backend.like.service;

import cotato.backend.application.service.ApplicationService;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.common.exception.ValidationException;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.repository.ApplicationRepository;
import cotato.backend.domain.like.entity.ApplicationLike;
import cotato.backend.domain.like.repository.ApplicationLikeRepository;
import cotato.backend.domain.staff.entity.Staff;
import cotato.backend.domain.staff.repository.StaffRepository;
import cotato.backend.like.dto.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationLikeService {

    private final ApplicationLikeRepository applicationLikeRepository;
    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;
    private StaffRepository staffRepository;

    /**
     * 좋아요 추가
     */
    @Transactional
    public LikeResponse addLike(Long applicationId, Long staffId){
        if(applicationLikeRepository.existsByApplicationIdAndStaffId(applicationId, staffId)){
            throw new ValidationException(ErrorCode.DUPLICATE_LIKE);
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STAFF_NOT_FOUND));

        ApplicationLike like = ApplicationLike.builder()
                .application(application)
                .staff(staff)
                .build();

        ApplicationLike savedLike = applicationLikeRepository.save(like);

        application.incrementLikeCount();

        return LikeResponse.from(savedLike);

    }

    /**
     * 좋아요 취소
     */
    @Transactional
    public void removeLike(Long applicationId, Long staffId){
        ApplicationLike applicationLike = applicationLikeRepository.findByApplicationIdAndStaffId(applicationId, staffId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.LIKE_NOT_FOUND));

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));

        applicationLikeRepository.delete(applicationLike);

        application.decrementLikeCount();
    }
}
