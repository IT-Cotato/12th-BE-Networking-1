package cotato.backend.domain.like.entity;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.staff.entity.Staff;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "application_likes",
         uniqueConstraints = {@UniqueConstraint(columnNames = {"application_id", "staff_id"})
    }
)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ApplicationLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Builder
    public ApplicationLike(Application application, Staff staff) {
        this.application = application;
        this.staff = staff;
    }
}
