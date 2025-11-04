package cotato.backend.domain.entity;

public enum AdminRole {
    PARTJANG("파트장"),
    PLAN_LEAD("기획팀장"),
    PR_LEAD("홍보팀장"),
    VICE_PRESIDENT("부회장"),
    PRESIDENT("회장"),
    EDUCATION_LEAD("교육팀장");

    private final String kor;
    AdminRole(String kor) { this.kor = kor; }
    public String kor() { return kor; }

    public static AdminRole fromCode(String code) {
        if (code == null) throw new IllegalArgumentException("role is null");
        return switch (code.trim().toLowerCase()) {
            case "partjang" -> PARTJANG;
            case "plan", "plan_lead", "planlead", "기획팀장" -> PLAN_LEAD;
            case "pr", "pr_lead", "prlead", "홍보팀장" -> PR_LEAD;
            case "vice", "vice_president", "vicepresident", "부회장" -> VICE_PRESIDENT;
            case "president", "회장" -> PRESIDENT;
            case "edu", "education_lead", "educationlead", "교육팀장" -> EDUCATION_LEAD;
            default -> throw new IllegalArgumentException("유효하지 않은 역할 코드입니다.");
        };
    }
}
