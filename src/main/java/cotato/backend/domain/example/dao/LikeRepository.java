package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Admin;
import cotato.backend.domain.example.entity.ApplicationForm;
import cotato.backend.domain.example.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByAdminAndApplicationForm(Admin admin, ApplicationForm applicationForm);
}
