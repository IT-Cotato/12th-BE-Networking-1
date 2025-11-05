package cotato.backend.domain.application.dao;

import cotato.backend.domain.application.entity.ApplicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    // 전체 리스트 조회
    @Query("SELECT a FROM ApplicationEntity a JOIN FETCH a.applicant app")
    Page<ApplicationEntity> findAllWithApplicant(Pageable pageable);

    // 상세 조회
    @Query("SELECT a FROM ApplicationEntity a JOIN FETCH a.applicant app WHERE a.id = :id")
    Optional<ApplicationEntity> findByIdWithApplicant(@Param("id") Long id);

    // 지원기수별 조회
    @Query("SELECT a FROM ApplicationEntity a JOIN FETCH a.applicant WHERE a.period = :period")
    Page<ApplicationEntity> findByPeriod(Integer period, Pageable pageable);

    // 좋아요순 조회
    @Query("SELECT a FROM ApplicationEntity a JOIN FETCH a.applicant ORDER BY a.likeCount DESC")
    Page<ApplicationEntity> findAllOrderByLikeCountDesc(Pageable pageable);

    // 지원기수+좋아요순 조회
    @Query("SELECT a FROM ApplicationEntity a JOIN FETCH a.applicant WHERE a.period = :period ORDER BY a.likeCount DESC")
    Page<ApplicationEntity> findByPeriodOrderByLikeCountDesc(@Param("period") Integer period, Pageable pageable);

}