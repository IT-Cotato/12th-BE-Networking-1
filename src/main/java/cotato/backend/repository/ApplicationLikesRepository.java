package cotato.backend.repository;

import cotato.backend.domain.Admin;
import cotato.backend.domain.Application;
import cotato.backend.domain.ApplicationLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationLikesRepository extends JpaRepository<ApplicationLikes, Long> {

    boolean existsByApplicationAndAdmin(Application application, Admin admin);
    List<ApplicationLikes> findByApplication(Application application);
}
