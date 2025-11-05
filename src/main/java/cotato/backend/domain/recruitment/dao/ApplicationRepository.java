package cotato.backend.domain.recruitment.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cotato.backend.api.dto.response.ApplicationListDTO;
import cotato.backend.domain.recruitment.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

	// ApplicationId로 application과 applicant 함께 조회
	// LikeService에서 지연 로딩하는 findByApplicationId가 필요하므로 중복된 네이밍 변경하였음
	@EntityGraph(attributePaths = "applicant")
	Optional<Application> findWithApplicantByApplicationId(Long applicationId);

	// 지연 로딩, Application만 조회함
	Optional<Application> findByApplicationId(Long applicationId);

	// 특정 기수의 Application을 찾아서 최신순으로 정렬함
	@Query("""
		SELECT new cotato.backend.api.dto.response.ApplicationListDTO(
			a.applicationId,
			a.applicant.name,
			a.period,
			a.part,
			COUNT(l)
		)
		FROM Application a
		LEFT JOIN a.likes l
		WHERE a.period = :period
		GROUP BY a.applicationId, a.applicant.name, a.period, a.part
		ORDER BY a.applicationTime DESC
	""")
	Page<ApplicationListDTO> findByPeriodOrderByApplicationTimeDesc(Integer period, Pageable pageable);

	// Application을 좋아요순으로 정렬함
	// 만약 좋아요 개수가 같은 Application이 있을 경우 최신순으로 정렬함
	@Query("""
		SELECT new cotato.backend.api.dto.response.ApplicationListDTO(
			a.applicationId,
			a.applicant.name,
			a.period,
			a.part,
			COUNT(l)
		)
		FROM Application a
		LEFT JOIN a.likes l
		GROUP BY a.applicationId, a.applicant.name, a.period, a.part
		ORDER BY COUNT(l) DESC, a.applicationTime DESC
	""")
	Page<ApplicationListDTO> findAllOrderByLikeCountDesc(Pageable pageable);

	// 특정 기수의 Application을 좋아요순으로 정렬함
	// 만약 좋아요 개수가 같은 Application이 있을 경우 최신순으로 정렬함
	@Query("""
		SELECT new cotato.backend.api.dto.response.ApplicationListDTO(
			a.applicationId,
			a.applicant.name,
			a.period,
			a.part,
			COUNT(l)
		)
		FROM Application a
		LEFT JOIN a.likes l
		WHERE a.period = :period
		GROUP BY a.applicationId, a.applicant.name, a.period, a.part
		ORDER BY COUNT(l) DESC, a.applicationTime DESC
	""")
	Page<ApplicationListDTO> findByPeriodOrderByLikeCountDesc(Integer period, Pageable pageable);
}
