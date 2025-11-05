package cotato.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 핸드폰번호가 같으면 동일한 지원자로 취급
    Optional<User> findByPhoneNumber(String phoneNumber);
}
