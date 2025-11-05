package cotato.backend.api.controller;

import cotato.backend.api.dto.request.ApplicationCreateRequest;
import cotato.backend.api.dto.response.ApplicationDetailResponse;
import cotato.backend.api.dto.response.ApplicationListItem;
import cotato.backend.domain.example.application.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ApplicationCreateRequest req) {
        Long id = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(java.util.Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ApplicationDetailResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public Page<ApplicationListItem> list(
            @RequestParam(defaultValue = "gisu") String filterBy,
            @RequestParam(required = false) Integer period,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return service.list(filterBy, period, PageRequest.of(page, pageSize));
    }

    @PostMapping("/{id}/likes")
    public ResponseEntity<Void> like(@PathVariable Long id, @RequestParam Long staffId) {
        service.like(id, staffId);
        return ResponseEntity.noContent().build();
    }
}
