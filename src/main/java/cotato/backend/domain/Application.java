package cotato.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private int period;  // 기수

    @Column(nullable = false)
    private int age; // 나이

    @Enumerated(EnumType.STRING)
    private Part part;  //파트

    @Column(nullable = false)
    private int ability; // 실력

    @Column(nullable = false)
    private int passion; // 열정

    @Column(nullable = false, length = 11)
    private String phoneNumber; // 전화번호

    private LocalDateTime applicationTime; // 제풀 시간
    private int likesNum; // 지원서 당 좋아요 수

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationLikes> likes = new ArrayList<>();

    public void increaseLikes() {
        this.likesNum++;
    }

    public void decreaseLikes() {
        if (this.likesNum > 0) this.likesNum--;
    }

    @PrePersist
    protected void onCreate() {
        this.applicationTime = LocalDateTime.now();
    }
}
