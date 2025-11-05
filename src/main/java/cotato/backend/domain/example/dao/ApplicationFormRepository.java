package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Long> {

    @Query(value = "SELECT * FROM application_form ORDER BY period DESC", nativeQuery = true)
    List<ApplicationForm> findAllOrderByPeriod(Pageable pageable);

    @Query(value = """
            SELECT a.*
            FROM application_form a
            LEFT JOIN likes l ON a.id = l.application_form_id
            GROUP BY a.id
            ORDER BY COUNT(l.id) DESC
            """, nativeQuery = true)
    List<ApplicationForm> findAllOrderByLikes(Pageable pageable);

    @Query(value = """
            SELECT a.*
            FROM application_form a
            LEFT JOIN likes l ON a.id = l.application_form_id
            GROUP BY a.id
            ORDER BY a.period DESC, COUNT(l.like_id) DESC
            """, nativeQuery = true)
    List<ApplicationForm> findAllOrderByPeriodAndLikes(Pageable pageable);
}
