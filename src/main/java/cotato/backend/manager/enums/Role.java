package cotato.backend.manager.enums;

import java.util.Arrays;

/**
 * 매니저 직책 Enum
 */
public enum Role {
    PARTJANG("파트장"),
    GIHOEGTEAMJANG("기획팀장"),
    HONGBOTEAMJANG("홍보팀장"),
    HOEJANG("회장"),
    BUHOEJANG("부회장"),
    GYOYUGTEAMJANG("교육팀장");

    private final String korean;

    Role(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

    /**
     * 문자열(한글 또는 영어) → Enum 변환
     */
    public static Role fromString(String input) {
        return Arrays.stream(Role.values())
                .filter(r -> r.name().equalsIgnoreCase(input)
                        || r.korean.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 역할명입니다: " + input));
    }
}