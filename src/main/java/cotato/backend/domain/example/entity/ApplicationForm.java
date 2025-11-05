package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "period")
    private Integer period;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "part")
    private Part part;

    @Column(name = "ability")
    private Integer ability;

    @Column(name = "passion")
    private Integer passion;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "application_time")
    private LocalDateTime applicationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @OneToMany(mappedBy = "applicationForm")
    private List<Like> likes = new ArrayList<>();

    public enum Part {
        PLANNER,
        DESIGNER,
        FRONTEND,
        BACKEND
    }

    public int countLikes() {
        if (likes == null)
            return 0;
        else
            return likes.size();
    }
}
