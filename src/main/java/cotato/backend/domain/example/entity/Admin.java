package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private AdminRole role;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
}
