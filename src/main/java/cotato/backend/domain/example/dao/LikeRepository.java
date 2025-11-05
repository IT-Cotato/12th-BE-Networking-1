package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Like;
import cotato.backend.domain.example.entity.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
    long countByApplication_ApplicationId(long applicationId);
    List<Like> findByApplication_ApplicationId(Long applicationId);
}
