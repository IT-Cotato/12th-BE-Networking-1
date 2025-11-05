package cotato.backend.dto.response;

import cotato.backend.domain.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationListResponse {

    private int page;
    private int pageSize;
    private String filterBy;
    private List<ApplicationResponse> applications;

    public ApplicationListResponse(int page, int pageSize, String filterBy, Page<Application> result) {
        this.page = page;
        this.pageSize = pageSize;
        this.filterBy = filterBy;
        this.applications = result.getContent().stream()
                .map(ApplicationResponse::new)
                .collect(Collectors.toList());
    }

}
