package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "applicant", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
@Getter @NoArgsConstructor
public class Applicant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 11)
    private String phone;

    @CreationTimestamp private LocalDateTime createdAt;
    @UpdateTimestamp private LocalDateTime updatedAt;

    @Builder
    public Applicant(String name, Integer age, String phone) {
        this.name = name; this.age = age; this.phone = phone;
    }

    public void update(String name, Integer age, String phone) {
        this.name = name; this.age = age; this.phone = phone;
    }
}