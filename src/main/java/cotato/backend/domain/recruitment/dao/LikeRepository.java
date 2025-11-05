package cotato.backend.domain.recruitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cotato.backend.domain.recruitment.entity.Application;
import cotato.backend.domain.recruitment.entity.Like;
import cotato.backend.domain.recruitment.entity.Management;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	boolean existsByApplicationAndManagement(Application application, Management management);
}
