package cotato.backend.domain.application;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PartConverter implements AttributeConverter<Part, String> {

    @Override
    public String convertToDatabaseColumn(Part part) {
        if (part== null) return null;
        return part.getDescription();
    }

    @Override
    public Part convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        for (Part part : Part.values()) {
            if (part.getDescription().equals(dbData)) {
                return part;
            }
        }
        throw new IllegalArgumentException("Unknown part: " + dbData);
        }
}