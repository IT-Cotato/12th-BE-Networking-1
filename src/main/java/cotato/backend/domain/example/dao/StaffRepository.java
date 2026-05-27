package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}