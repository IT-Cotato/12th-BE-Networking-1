package cotato.backend.domain.example.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    PARTJANG,
    기획팀장,
    홍보팀장,
    부회장,
    회장,
    교육팀장;

    @JsonCreator
    public static Role from(Object raw) {
        if (raw == null) return null;
        String s = raw.toString().trim();

        // 1) 정확 일치
        for (Role r : values()) if (r.name().equals(s)) return r;

        // 2) 대소문자 무시 (ex: partjang)
        String upper = s.toUpperCase();
        for (Role r : values()) if (r.name().equalsIgnoreCase(upper)) return r;

        // 3) 한글 별칭 매핑
        switch (s) {
            case "파트장" -> { return PARTJANG; }
            case "기획장", "기획" -> { return 기획팀장; }
            case "홍보장", "홍보" -> { return 홍보팀장; }
            case "부회" -> { return 부회장; }
            case "회장님" -> { return 회장; }
            case "교육장", "교육" -> { return 교육팀장; }
        }
        throw new IllegalArgumentException("Unknown role: " + s);
    }

    @JsonValue
    public String toValue() {
        return this.name(); // 응답은 항상 enum 이름으로
    }
}
