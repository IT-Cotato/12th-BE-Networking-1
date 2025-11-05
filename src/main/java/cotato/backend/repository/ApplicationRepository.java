package cotato.backend.repository;

import cotato.backend.domain.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // 1. 기수 기준 정렬
    Page<Application> findByPeriodOrderByApplicationIdAsc(int period, Pageable pageable);

    // 2. 좋아요수 기준 정렬
    Page<Application> findAllByOrderByLikesNumDesc(Pageable pageable);

    // 3. 기수 + 좋아요수
    Page<Application> findByPeriodOrderByLikesNumDesc(int period, Pageable pageable);
}
