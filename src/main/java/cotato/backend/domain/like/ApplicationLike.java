package cotato.backend.domain.like;

import cotato.backend.domain.application.Application;
import cotato.backend.domain.staff.StaffDetail;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "application_like")
@IdClass(ApplicationLikeId.class) //ID 클래스 지정
public class ApplicationLike {

    @Id // 복합키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Id // 복합키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_user_id")
    private StaffDetail staff;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public ApplicationLike(Application application, StaffDetail staff) {
        this.application = application;
        this.staff = staff;
    }
}