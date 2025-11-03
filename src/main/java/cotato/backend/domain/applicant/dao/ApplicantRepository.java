package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Optional<Applicant> findByPhoneNum(String phoneNum);
}
