package cotato.backend.domain.recruitment.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Part {
	PM("기획"),
	DE("디자이너"),
	FE("프론트엔드"),
	BE("백엔드");

	private final String label;

	Part(String label) {
		this.label = label;
	}

	@JsonValue
	public String getLabel() {
		return label;
	}
}
