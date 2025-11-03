package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.entity.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.domain.example.entity.ExampleEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {

    // 핸드폰번호 겹치는지 확인
    Optional<ApplicantEntity> findByPhoneNumber(String phoneNumber);
}