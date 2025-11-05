package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.example.application.ApplicationFormService;
import cotato.backend.domain.example.dto.request.ApplicationFormRequest;
import cotato.backend.domain.example.dto.response.ApplicationFormListResponse;
import cotato.backend.domain.example.dto.response.ApplicationFormResponse;
import cotato.backend.domain.example.entity.ApplicationForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application-forms")
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    @PostMapping
    public DataResponse<Long> createForm(@Valid @RequestBody ApplicationFormRequest req) {
        Long id = applicationFormService.create(req);
        return DataResponse.created(id);
    }

    @GetMapping("/{id}")
    public DataResponse<ApplicationFormResponse> getForm(@PathVariable Long id) {
        ApplicationFormResponse response = applicationFormService.get(id);
        return DataResponse.from(response);
    }

    @GetMapping
    public DataResponse<List<ApplicationFormListResponse>> getFilteredForms(
            @RequestParam String filterBy,
            @RequestParam int page,
            @RequestParam int pageSize
    ) {
        List<ApplicationFormListResponse> responses =
                applicationFormService.getFilteredForms(filterBy, page, pageSize);
        return DataResponse.from(responses);
    }
}
