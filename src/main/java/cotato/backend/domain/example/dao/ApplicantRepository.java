package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Applicant findByPhoneNumber(String phoneNumber);
}
