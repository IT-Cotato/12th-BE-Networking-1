package cotato.backend.domain.application.repository;

import cotato.backend.domain.application.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // 기수별로 조회 (페이징)
    Page<Application> findByPeriod(Integer period, Pageable pageable);

    // 좋아요 수 높은 순으로 조회 (페이징)
    Page<Application> findAllByOrderByLikeCountDesc(Pageable pageable);

    // 기수 + 좋아요 수 높은 순으로 조회 (페이징)
    Page<Application> findByPeriodOrderByLikeCountDesc(Integer period, Pageable pageable);

    // 지원자별 지원 내역 조회
    List<Application> findByApplicantId(Long applicantId);
}