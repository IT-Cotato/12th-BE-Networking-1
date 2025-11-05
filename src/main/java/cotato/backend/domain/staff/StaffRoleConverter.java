package cotato.backend.domain.staff;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// DB에는 "파트장"(String) <-> Java에는 StaffRole(Enum)
@Converter(autoApply = true)
public class StaffRoleConverter implements AttributeConverter<StaffRole,String> {

    // 1. Enum -> DB STring (예: StaffRole.PART_LEAD -> "파트장")
    @Override
    public String convertToDatabaseColumn(StaffRole staffRole){
        if(staffRole==null){
            return null;
        }
        return staffRole.getDescription();
    }

    // 2. DB String -> Enum (예: "파트장" -> StaffRole.PART_LEAD)
    @Override
    public StaffRole convertToEntityAttribute(String dbData){
        if(dbData==null)return null;

        StaffRole[] roles = StaffRole.values();

        for(StaffRole role:roles){
            if(role.getDescription().equals(dbData)){
                return role;
            }
        }

        throw new IllegalArgumentException("일치하는 StaffRole을 찾을 수 없습니다.");
    }
}
