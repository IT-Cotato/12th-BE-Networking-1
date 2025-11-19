package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // 특정 지원서 + 운영진 조합으로 이미 좋아요 눌렀는지 체크
    boolean existsByAdminIdAndApplicationId(Long adminId, Long applicationId);

    // 특정 운영진이 특정 지원서에 누른 좋아요 조회
    java.util.Optional<Like> findByAdminIdAndApplicationId(Long adminId, Long applicationId);

    // 특정 운영진이 특정 지원서에 누른 좋아요 삭제
    void deleteByAdminIdAndApplicationId(Long adminId, Long applicationId);
}
