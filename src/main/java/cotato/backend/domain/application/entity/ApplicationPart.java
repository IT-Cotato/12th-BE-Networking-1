package cotato.backend.domain.application.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationPart {
    PLANNER("planner"),
    DESIGNER("designer"),
    FRONTEND("frontend"),
    BACKEND("backend");

    @JsonValue
    private final String englishCode;

    private ApplicationPart(String englishCode) {
        this.englishCode = englishCode;
    }

    public String getEnglishCode() {
        return englishCode;
    }
}

