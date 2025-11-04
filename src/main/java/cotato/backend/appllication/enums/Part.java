package cotato.backend.appllication.enums;

import java.util.Arrays;

public enum Part {
    PM("기획"),
    DESIGN("디자이너"),
    FE("프론트엔드"),
    BE("백엔드");

    private final String korean;

    Part(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

    /** String → Enum 변환 (한글, 영어 모두 허용) */
    public static Part fromString(String input) {
        return Arrays.stream(Part.values())
                .filter(p -> p.name().equalsIgnoreCase(input)
                        || p.korean.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 파트명입니다: " + input));
    }
}
