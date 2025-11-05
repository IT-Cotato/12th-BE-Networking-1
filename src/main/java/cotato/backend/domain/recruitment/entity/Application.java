package cotato.backend.domain.recruitment.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cotato.backend.domain.recruitment.entity.enums.Part;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Application {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long applicationId;

	@Min(1)
	@Column(nullable = false)
	private int period;

	// DB에는 PM, DE, FE, BE으로 저장됨
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Part part;

	@Min(0) @Max(10)
	@Column(nullable = false)
	private int ability;

	@Min(0) @Max(10)
	@Column(nullable = false)
	private int passion;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(nullable = false)
	private LocalDateTime applicationTime;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Like> likes = new ArrayList<>();
}