package cotato.backend.common.converter;

import cotato.backend.domain.application.entity.ApplicationPart;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter
public class ApplicationPartConverter implements AttributeConverter<ApplicationPart, String> {

    // Entity를 DB에 저장
    @Override
    public String convertToDatabaseColumn(final ApplicationPart part) {
        if (part == null) {
            return null;
        }
        return part.getEnglishCode();
    }

    // DB에서 조회된 값으로 Entity를 생성
    @Override
    public ApplicationPart convertToEntityAttribute(final String englishCode) {
        if (englishCode == null) {
            return null;
        }
        return Stream.of(ApplicationPart.values())
                .filter(part -> part.getEnglishCode().equalsIgnoreCase(englishCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("DB에 유효하지 않은 Part 코드가 있습니다"));
    }
}
