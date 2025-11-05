package cotato.backend.domain.applicant.entity;

import cotato.backend.domain.application.entity.ApplicationEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicant_id")
	private Long id;

	@Column(nullable = false, length = 10)
	private String name;

    @Column(nullable = false)
    private int age;

    @Column(name = "phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;

    // 지원서 관계 설정
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationEntity> applications = new ArrayList<>();

    @Builder
	public ApplicantEntity(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.applications = new ArrayList<>();
    }

    public void update(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}