package cotato.backend.domain.repository;

import cotato.backend.domain.entity.ApplicationLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationLikeRepository extends JpaRepository<ApplicationLike, Long> {
    boolean existsByApplicationIdAndAdminId(Long applicationId, Long adminId);
    long countByApplicationId(Long applicationId);
}
