package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.ApplicationLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationLikeRepository extends JpaRepository<ApplicationLike, Long> {
    boolean existsByApplicationIdAndStaffId(Long applicationId, Long staffId);
    long countByApplicationId(Long applicationId);
}