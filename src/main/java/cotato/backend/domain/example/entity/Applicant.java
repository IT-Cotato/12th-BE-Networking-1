package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;
    private String name;
    private Integer age;

    // 재지원자는 폰번호로 식별
    @Column(unique=true, nullable=false)
    private String phoneNumber;
}
