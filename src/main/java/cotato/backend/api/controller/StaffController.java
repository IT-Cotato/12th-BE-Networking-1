package cotato.backend.api.controller;

import cotato.backend.api.dto.request.StaffUpsertRequest;
import cotato.backend.domain.example.application.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService service;

    @PostMapping
    public Map<String, Long> create(@Valid @RequestBody StaffUpsertRequest req) {
        return Map.of("id", service.create(req));
    }

    @PutMapping("/{id}")
    public Map<String, Long> update(@PathVariable Long id, @Valid @RequestBody StaffUpsertRequest req) {
        return Map.of("id", service.update(id, req));
    }

    @GetMapping("/{id}")
    public StaffUpsertRequest get(@PathVariable Long id) {
        return service.get(id);
    }
}
