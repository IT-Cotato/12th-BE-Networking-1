package cotato.backend.domain.like.repository;

import cotato.backend.domain.like.entity.ApplicationLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationLikeRepository extends JpaRepository<ApplicationLike, Long> {

    // 특정 운영진이 특정 서류에 좋아요 했는지 확인
    boolean existsByApplicationIdAndStaffId(Long applicationId, Long staffId);

    // 특정 운영진이 특정 서류에 누른 좋아요 찾기 (좋아요 취소용)
    Optional<ApplicationLike> findByApplicationIdAndStaffId(Long applicationId, Long staffId);

    // 특정 서류의 모든 좋아요 개수
    long countByApplicationId(Long applicationId);
}