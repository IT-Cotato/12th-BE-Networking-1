package cotato.backend.domain.applicant.repository;

import cotato.backend.domain.applicant.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    // 핸드폰 번호로 지원자 찾기 (재지원 확인용)
    Optional<Applicant> findByPhoneNumber(String phoneNumber);

    // 핸드폰 번호 중복 체크
    boolean existsByPhoneNumber(String phoneNumber);
}