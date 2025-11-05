package cotato.backend.domain.application;

import cotato.backend.common.dto.BaseResponse;
import cotato.backend.domain.application.dto.ApplicationSubmitRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Long> submitApplication(
            @Valid @RequestBody ApplicationSubmitRequest request
    ) {
        Long savedApplicationId = applicationService.submitApplication(request);
        return ResponseEntity.ok(savedApplicationId);
    }
}