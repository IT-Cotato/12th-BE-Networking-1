package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // “자동 기록 기능을 작동시켜주는 감시자”
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer period;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String part;

    @Column(nullable = false)
    private Integer ability;

    @Column(nullable = false)
    private Integer passion;

    @Column(nullable = false)
    private String phoneNumber;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime applicationTime;

    @Column(nullable = false)
    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @OneToMany(mappedBy = "application")
    private List<Like> likes = new ArrayList<>();
}
