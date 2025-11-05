package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Application;
import cotato.backend.domain.example.entity.ApplicationLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select a from Application a where a.period = :period")
    Page<Application> findByPeriod(@Param("period") Integer period, Pageable pageable);

    @Query("""
		select a from Application a
		left join ApplicationLike l on l.application = a
		group by a
		order by count(l) desc, a.id desc
	""")
    Page<Application> findAllOrderByLikes(Pageable pageable);

    @Query("""
		select a from Application a
		left join ApplicationLike l on l.application = a
		where a.period = :period
		group by a
		order by count(l) desc, a.id desc
	""")
    Page<Application> findByPeriodOrderByLikes(@Param("period") Integer period, Pageable pageable);
}
