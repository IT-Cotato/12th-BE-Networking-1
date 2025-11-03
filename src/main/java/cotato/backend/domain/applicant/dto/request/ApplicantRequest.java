package cotato.backend.domain.applicant.dto.request;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.staff.entity.StaffEntity;

public record ApplicantRequest(String name, int age, String phoneNumber) {
//    public ApplicantEntity toEntity() {
//        return ApplicantEntity.builder()
//                .name(name)
//                .age(age)
//                .phoneNumber(phoneNumber)
//                .build();
//    }
}
