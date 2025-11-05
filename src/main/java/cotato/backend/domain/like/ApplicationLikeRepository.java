package cotato.backend.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationLikeRepository extends JpaRepository<ApplicationLike, ApplicationLikeId> {
    // 복합 키로 (applicationId, staffId)가 존재하는지 확인 (중복 방지용)
    boolean existsByApplicationIdAndStaffId(Long applicationId, Long staffId);
}
