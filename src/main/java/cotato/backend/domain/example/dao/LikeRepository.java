package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // 특정 지원서 + 운영진 조합으로 이미 좋아요 눌렀는지 체크
    boolean existsByAdminIdAndApplicationId(Long adminId, Long applicationId);
}
