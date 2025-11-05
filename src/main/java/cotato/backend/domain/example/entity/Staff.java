package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Getter @NoArgsConstructor
public class Staff {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 11)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreationTimestamp private LocalDateTime createdAt;
    @UpdateTimestamp private LocalDateTime updatedAt;

    @Builder
    public Staff(String name, Integer age, String phone, Role role) {
        this.name = name; this.age = age; this.phone = phone; this.role = role;
    }

    public void update(String n, Integer a, String p, Role r){
        this.name=n; this.age=a; this.phone=p; this.role=r;
    }
}
