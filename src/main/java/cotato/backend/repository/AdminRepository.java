package cotato.backend.repository;

import cotato.backend.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

}
