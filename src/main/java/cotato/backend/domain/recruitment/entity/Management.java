package cotato.backend.domain.recruitment.entity;

import java.util.ArrayList;
import java.util.List;

import cotato.backend.domain.recruitment.entity.enums.ManagementRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Management {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long managementId;

	@Column(length = 10, nullable = false)
	@Size(min = 2, max = 10)
	private String name;

	// 운영진은 최소 나이만 설정
	// 만약 30살에 지원해서 활동을 시작했을 경우 30살 초과일 수 있기 때문
	@Min(22)
	@Column(nullable = false)
	private int age;

	@Column(length = 11, nullable = false)
	private String phoneNumber;

	// partjang, planning_lead, pr_lead, vice_president, president, edu_lead
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ManagementRole managementRole;

	@OneToMany(mappedBy = "management", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Like> likes = new ArrayList<>();
}