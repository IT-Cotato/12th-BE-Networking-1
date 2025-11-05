package cotato.backend.api.dto.request;

import cotato.backend.domain.recruitment.entity.enums.Part;

public record SubmitApplicationDTO(
	String name, Integer period, Integer age, Part part,
	Integer ability, Integer passion, String phoneNumber
) {
}
