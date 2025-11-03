package cotato.backend.applicant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @Column(name = "phone_number", length = 11, nullable = false)
    private String phoneNumber;

    @Column(name = "applicant_name", length = 10, nullable = false)
    private String name;

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;
}
