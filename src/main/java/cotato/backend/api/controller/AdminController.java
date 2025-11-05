package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.example.application.AdminService;
import cotato.backend.domain.example.dto.request.AdminRequest;
import cotato.backend.domain.example.dto.response.AdminResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public DataResponse<DefaultIdResponse> createAdmin(@Valid @RequestBody AdminRequest req) {

        Long id = adminService.create(req);
        return DataResponse.created(DefaultIdResponse.of(id));
    }

    @GetMapping("/{id}")
    public DataResponse<AdminResponse> getAdmin(@PathVariable Long id) {

        AdminResponse res = adminService.get(id);
        return DataResponse.from(res);
    }

    @PutMapping("/{id}")
    public DataResponse<AdminResponse> updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminRequest req) {
        AdminResponse res = adminService.update(id, req);
        return DataResponse.from(res);
    }

}
