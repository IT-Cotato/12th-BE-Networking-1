package cotato.backend.likes.repository;

import cotato.backend.appllication.entity.Application;
import cotato.backend.likes.entity.Likes;
import cotato.backend.manager.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    boolean existsByApplicationAndManager(Application application, Manager manager);

    Optional<Likes> findByApplicationAndManager(Application application, Manager manager);
}

