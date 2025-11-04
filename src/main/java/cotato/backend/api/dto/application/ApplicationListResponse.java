package cotato.backend.api.dto.application;

import java.util.List;

public record ApplicationListResponse(
        int page,
        int pageSize,
        long totalElements,
        int totalPages,
        List<Row> content
) {
    public record Row(
            Long id,
            String applicantName,
            Integer period,
            String part,
            Integer likeCount
    ) { }
}
