package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    // 전화번호로 기존 지원자 조회 (재지원 여부 확인용)
    Optional<Applicant> findByPhoneNumber(String phoneNumber);
}
