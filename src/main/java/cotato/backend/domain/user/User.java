package cotato.backend.domain.user;

import cotato.backend.domain.application.Application;
import cotato.backend.domain.staff.StaffDetail;
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
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role; // APPLICANT, STAFF

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(name="phone_number",nullable = false,unique = true, length= 11)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(name="password_hash")
    private String passwordHash;

    // 1:1 식별 관계 (User가 StaffDetail의 주인)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StaffDetail staffDetail;

    // 1:N 비식별 관계 (User가 Application의 주인)
    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<>();

    @Builder
    public User(Role role, String name, Integer age, String phoneNumber) {
        this.role = role;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // 명세: "재지원시... 지원자 정보는 수정 가능"
    public void updateProfile(String name, Integer age,String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}
