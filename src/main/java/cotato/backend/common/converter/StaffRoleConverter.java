package cotato.backend.common.converter;

import cotato.backend.domain.staff.entity.StaffRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter
public class StaffRoleConverter implements AttributeConverter<StaffRole, String> {

    // Entity를 DB에 저장
    @Override
    public String convertToDatabaseColumn(final StaffRole role) {
        if (role == null) {
            return null;
        }
        return role.getEnglishCode();
    }

    // DB에서 조회된 값으로 Entity를 생성
    @Override
    public StaffRole convertToEntityAttribute(final String englishCode) {
        if (englishCode == null) {
            return null;
        }

        return Stream.of(StaffRole.values())
                .filter(role -> role.getEnglishCode().equalsIgnoreCase(englishCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("DB에 유효하지 않은 Role 코드가 있습니다"));
    }
}
