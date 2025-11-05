package cotato.backend.domain.recruitment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cotato.backend.domain.recruitment.entity.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	Optional<Applicant> findByPhoneNumber(String phoneNumber);
	Optional<Applicant> findByApplicantId(Long applicantId);
}
