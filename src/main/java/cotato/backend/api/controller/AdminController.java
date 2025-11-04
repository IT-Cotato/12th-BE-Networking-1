package cotato.backend.api.controller;

import cotato.backend.api.dto.admin.AdminRequest;
import cotato.backend.api.dto.admin.AdminResponse;
import cotato.backend.domain.entity.Admin;
import cotato.backend.domain.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public AdminResponse create(@RequestBody @Valid AdminRequest req) {
        Admin a = adminService.create(req.name(), req.age(), req.phoneNumber(), req.role());
        return toRes(a);
    }

    @GetMapping("/{id}")
    public AdminResponse get(@PathVariable Long id) {
        return toRes(adminService.get(id));
    }

    @PutMapping("/{id}")
    public AdminResponse update(@PathVariable Long id, @RequestBody @Valid AdminRequest req) {
        Admin a = adminService.update(id, req.name(), req.age(), req.phoneNumber(), req.role());
        return toRes(a);
    }

    private static AdminResponse toRes(Admin a) {
        return new AdminResponse(a.getId(), a.getName(), a.getAge(), a.getPhoneNumber(), a.getRole().name().toLowerCase());
    }
}
