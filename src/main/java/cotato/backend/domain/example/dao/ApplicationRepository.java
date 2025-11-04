package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // 지원 기수별로 10건씩 조회
    List<Application> findByPeriodOrderByApplicationTimeDesc(int period, Pageable pageable);

    // 좋아요 수가 높은 순서대로 10건씩 조회
    @Query("""
        SELECT a FROM Application a
        LEFT JOIN a.likes l
        GROUP BY a.id
        ORDER BY COUNT(l) DESC, a.applicationTime DESC
    """)
    List<Application> findTopByLikes(Pageable pageable);

    // 지원기수와 좋아요 수를 함께 고려해 10건씩 조회
    @Query("""
        SELECT a FROM Application a
        LEFT JOIN a.likes l
        WHERE a.period = :period
        GROUP BY a.id
        ORDER BY COUNT(l) DESC, a.applicationTime DESC
    """)
    List<Application> findByPeriodOrderByLikesDesc(int period, Pageable pageable);
}
