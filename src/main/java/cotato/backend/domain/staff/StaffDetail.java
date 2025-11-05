package cotato.backend.domain.staff;

import cotato.backend.domain.like.ApplicationLike;
import cotato.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "staff_details")
@Convert(converter = StaffRoleConverter.class) // 컨버터 적용
public class StaffDetail {

    @Id
    public Long id;

    // User 엔티티의 ID를 이 엔티티의 ID로 사용
    @MapsId  //부모의 ID를 받아서, 기본키로 사용하겠다는 의미
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    @Column(name="role_code",nullable = false)
    public StaffRole roleCode;

    @OneToMany(mappedBy = "staff")
    private List<ApplicationLike> likes = new ArrayList<>();

    @Builder
    public StaffDetail(User user, StaffRole roleCode) {
        this.user = user;
        this.roleCode = roleCode;
    }

    public void updateRole(StaffRole newRole) {
        this.roleCode = newRole;
    }
}
