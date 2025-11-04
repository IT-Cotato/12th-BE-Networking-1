package cotato.backend.domain.member.entity;

import cotato.backend.common.entity.BaseEntity;
import cotato.backend.common.enums.Part;
import cotato.backend.domain.member.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "generation", nullable = false)
    private Integer generation;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "part", nullable = false)
    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(name = "phone_num", nullable = false, length = 11)
    private String phoneNum;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.MEMBER;
}
