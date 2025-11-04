package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int period;
    private int age;
    private String part;
    private int ability;
    private int passion;
    private String phoneNumber;
    @CreatedDate
    private LocalDateTime applicationTime;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
