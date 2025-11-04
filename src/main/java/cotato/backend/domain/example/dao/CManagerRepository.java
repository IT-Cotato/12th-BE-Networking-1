package cotato.backend.domain.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import cotato.backend.domain.example.entity.CManager;

public interface CManagerRepository extends JpaRepository<CManager, Long> {

}
