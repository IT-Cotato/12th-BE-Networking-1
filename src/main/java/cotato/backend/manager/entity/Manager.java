package cotato.backend.manager.entity;

import cotato.backend.likes.entity.Likes;
import cotato.backend.manager.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @Column(name = "manager_name", nullable = false)
    private String name;

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likesList = new ArrayList<>();

    public void updateManager(String name, String phoneNumber, Integer birthYear, Role role) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthYear = birthYear;
        this.role = role;
    }
}
