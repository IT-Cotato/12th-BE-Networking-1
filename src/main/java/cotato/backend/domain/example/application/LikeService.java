package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.AdminRepository;
import cotato.backend.domain.example.dao.ApplicationRepository;
import cotato.backend.domain.example.dao.LikeRepository;
import cotato.backend.domain.example.entity.Admin;
import cotato.backend.domain.example.entity.Application;
import cotato.backend.domain.example.entity.Like;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeService {

    private final LikeRepository likeRepository;
    private final AdminRepository adminRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    // 운영진이 특정 지원서에 좋아요를 추가 
    // like 엔티티 생성 후 지원서의 likeCount 증가
    // 중복 좋아요는 무시
    public Long like(Long adminId, Long applicationId) {
        if (likeRepository.existsByAdminIdAndApplicationId(adminId, applicationId)) {
            return null; // 이미 좋아요 누른 상태라면 null 반환하고 메서드 종료
        }

        // 처음 누르는 좋아요일 경우
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        Like like = Like.builder()
                .admin(admin)
                .application(application)
                .build();

        application.setLikeCount(application.getLikeCount() + 1);

        return likeRepository.save(like).getId();
    }

    @Transactional
    // 좋아요 취소
    public void unlike(Long adminId, Long applicationId) {
        likeRepository.findByAdminIdAndApplicationId(adminId, applicationId)
                .ifPresent(like -> {
                    Application application = like.getApplication();
                    if (application != null) {
                        int current = application.getLikeCount();
                        application.setLikeCount(Math.max(0, current - 1));
                    }
                    likeRepository.delete(like);
                });
    }
}


