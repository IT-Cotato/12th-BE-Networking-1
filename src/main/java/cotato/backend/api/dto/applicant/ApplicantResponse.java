package cotato.backend.api.dto.applicant;

public record ApplicantResponse(
        Long id,
        String name,
        Integer age,
        String phoneNumber
) { }
