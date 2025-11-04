package cotato.backend.api.dto.application;

public record ApplicationDetailResponse(
        Long id,
        String name,
        Integer period,
        Integer age,
        String part,
        Integer ability,
        Integer passion,
        String phoneNumber,
        String applicationTime,
        Integer likeCount
) { }
