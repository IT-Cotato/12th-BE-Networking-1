package cotato.backend.domain.repository;

import cotato.backend.domain.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByPeriodOrderByApplicationTimeDesc(Integer period, Pageable pageable);
    Page<Application> findAllByOrderByLikeCountDescApplicationTimeDesc(Pageable pageable);
    Page<Application> findByPeriodOrderByLikeCountDescApplicationTimeDesc(Integer period, Pageable pageable);
}
