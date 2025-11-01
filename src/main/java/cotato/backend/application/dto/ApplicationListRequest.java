package cotato.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationListRequest {
    private String filterBy;  // "likes", "period", "period+likes"
    private Integer period;
    private Integer page;
    private Integer pageSize;
}
