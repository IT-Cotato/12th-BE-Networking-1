package cotato.backend.domain.entity;

public enum Part {
    PLAN("기획"),
    DESIGN("디자이너"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");

    private final String kor;
    Part(String kor) { this.kor = kor; }
    public String kor() { return kor; }

    public static Part fromKor(String value) {
        if (value == null) throw new IllegalArgumentException("part is null");
        return switch (value.trim()) {
            case "기획" -> PLAN;
            case "디자이너" -> DESIGN;
            case "프론트엔드" -> FRONTEND;
            case "백엔드" -> BACKEND;
            default -> throw new IllegalArgumentException("지원 파트는 기획/디자이너/프론트엔드/백엔드 중 하나여야 합니다.");
        };
    }
}
