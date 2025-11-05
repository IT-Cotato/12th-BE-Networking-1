package cotato.backend.domain.staff.entity;

import cotato.backend.common.converter.StaffRoleConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "staff")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StaffEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private Long id;

	@Column(nullable = false, length = 10)
	private String name;

    @Column(nullable = false)
    private int age;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Convert(converter = StaffRoleConverter.class)
    @Column(nullable = false, length = 20)
    private StaffRole role;

	@Builder
	public StaffEntity(String name, int age, String phoneNumber, StaffRole role) {
		this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.role = role;
	}

    public void update(String name, int age, String phoneNumber, StaffRole role) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

}