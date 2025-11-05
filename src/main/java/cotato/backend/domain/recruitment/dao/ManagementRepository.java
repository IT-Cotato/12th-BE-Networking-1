package cotato.backend.domain.recruitment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cotato.backend.domain.recruitment.entity.Applicant;
import cotato.backend.domain.recruitment.entity.Management;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Long> {
	Optional<Management> findByManagementId(Long managementId);
}
