package cotato.backend.api.dto.request;

import cotato.backend.domain.example.entity.Part;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationCreateRequest {
    @NotBlank @Pattern(regexp = "^[가-힣]{2,10}$")
    private String name;

    @NotNull @Min(1)
    private Integer period;

    @NotNull @Min(22) @Max(30)
    private Integer age;

    @NotNull
    private Part part;

    @NotNull @Min(0) @Max(10)
    private Integer ability;

    @NotNull @Min(0) @Max(10)
    private Integer passion;

    @NotBlank @Pattern(regexp = "^010\\d{8}$")
    private String phoneNumber;

    @NotBlank
    private String applicationTime; // "yyyy-MM-dd HH:mm"
}
