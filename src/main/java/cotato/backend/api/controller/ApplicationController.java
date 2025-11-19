package cotato.backend.api.controller;

import cotato.backend.domain.example.application.ApplicationService;
import cotato.backend.domain.example.dto.request.ApplicationRequest;
import cotato.backend.domain.example.dto.response.ApplicationResponse;
import cotato.backend.domain.example.entity.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    // 지원서 생성, 생성된 id 반환
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ApplicationRequest request) {
        Long id = applicationService.createApplication(request);
        return ResponseEntity.ok(id); // HTTP 200 OK 상태코드와 함께 본문(body)을 반환
    }

    // 지원서 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getById(id));
    }

    // 필터/페이지 파라미터로 리스트 조회
    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> list(
            @RequestParam(name = "filterBy", defaultValue = "likes") String filterBy,
            @RequestParam(name = "period", required = false) Integer period,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        ApplicationService.FilterBy fb = switch (filterBy) {
            case "likes" -> ApplicationService.FilterBy.likes;
            case "gisu" -> ApplicationService.FilterBy.gisu;
            case "gisu+likes" -> ApplicationService.FilterBy.gisu_likes;
            default -> ApplicationService.FilterBy.likes;
        };
        return ResponseEntity.ok(applicationService.listApplications(fb, period, page, pageSize));
    }
}


