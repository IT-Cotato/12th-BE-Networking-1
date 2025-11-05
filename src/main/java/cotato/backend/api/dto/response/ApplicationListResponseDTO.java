package cotato.backend.api.dto.response;

import java.util.List;

import cotato.backend.domain.recruitment.entity.enums.FilterBy;

public record ApplicationListResponseDTO(
	FilterBy filterBy, Integer page,
	List<ApplicationListDTO> applicationListDTO
) {
}
