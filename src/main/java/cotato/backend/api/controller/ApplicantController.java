package cotato.backend.api.controller;

import cotato.backend.api.dto.request.ApplicantUpsertRequest;
import cotato.backend.domain.example.application.ApplicantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService service;

    @PostMapping("/upsert")
    public Map<String, Long> upsert(@Valid @RequestBody ApplicantUpsertRequest req) {
        return Map.of("id", service.upsert(req));
    }

    @GetMapping("/{id}")
    public ApplicantUpsertRequest get(@PathVariable Long id) {
        return service.get(id);
    }
}
