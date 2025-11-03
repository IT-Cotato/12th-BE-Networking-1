package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.ApplicationFormService;
import cotato.backend.domain.applicant.dto.request.ApplicationFormRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application-forms")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    @PostMapping
    public DataResponse<DefaultIdResponse> submit(@RequestBody ApplicationFormRequest request) {
        Long id = applicationFormService.submit(request);
        return DataResponse.created(DefaultIdResponse.of(id));
    }
}
