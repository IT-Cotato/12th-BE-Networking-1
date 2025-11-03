package cotato.backend.domain.like.entity;

import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.staff.entity.StaffEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "application_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_id")
	private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private StaffEntity staff;

	@Builder
	public LikeEntity(ApplicationEntity application, StaffEntity staff) {
		this.application = application;
        this.staff = staff;
	}
}