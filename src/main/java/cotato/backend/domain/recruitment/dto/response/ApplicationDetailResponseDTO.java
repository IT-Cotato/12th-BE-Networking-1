package cotato.backend.domain.recruitment.dto.response;

import java.time.LocalDateTime;

import cotato.backend.domain.recruitment.entity.enums.Part;

public record ApplicationDetailResponseDTO(
	String name, Integer period, Integer age, Part part,
	Integer ability, Integer passion, String phoneNumber, LocalDateTime applicationTime) {
}