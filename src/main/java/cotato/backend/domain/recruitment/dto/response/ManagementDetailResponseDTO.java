package cotato.backend.domain.recruitment.dto.response;

import cotato.backend.domain.recruitment.entity.enums.ManagementRole;

public record ManagementDetailResponseDTO(
	String name, Integer age, String phoneNumber, ManagementRole managementRole
) {
}
