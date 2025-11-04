package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Applicant;
import cotato.backend.domain.example.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepositoryImplementation<Application, Long> {
    Page<Application> findByPeriod(Integer period, Pageable pageable);
    Optional<Application> findByApplicantAndPeriod(Applicant applicant, Integer period);
}
