package cotato.backend.api.controller;

import cotato.backend.domain.example.application.AdminService;
import cotato.backend.domain.example.dto.request.UpsertAdminRequest;
import cotato.backend.domain.example.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/upsert")
    public ResponseEntity<Long> upsert(@RequestBody UpsertAdminRequest request) {
        Long id = adminService.upsert(request.getId(), request.getName(), request.getAge(), request.getPhoneNumber(), request.getRole());
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> get(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getById(id));
    }
}


