package cotato.backend.api.dto.admin;

public record AdminResponse(
        Long id,
        String name,
        Integer age,
        String phoneNumber,
        String role
) { }
