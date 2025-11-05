package cotato.backend.domain.staff.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StaffRole {
    PARTJANG("partjang"),
    PLANNING_LEADER("planning_leader"),
    PR_LEADER("pr_leader"),
    VICE_PRESIDENT("vice_president"),
    PRESIDENT("president"),
    EDUCATION_LEADER("education_leader");

    @JsonValue
    private final String englishCode;

    private StaffRole(String englishCode) {
        this.englishCode = englishCode;
    }

    public String getEnglishCode() {
        return englishCode;
    }
}
