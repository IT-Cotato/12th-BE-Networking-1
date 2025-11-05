package cotato.backend.api.dto.response;

import cotato.backend.domain.recruitment.entity.enums.Part;

public record ApplicationListDTO(
	Long applicationId,
	String name, Integer period,
	Part part, Long likeCount
) {
}
