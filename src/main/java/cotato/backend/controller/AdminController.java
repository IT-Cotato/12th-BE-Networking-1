package cotato.backend.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.dto.request.AdminRequest;
import cotato.backend.dto.response.AdminResponse;
import cotato.backend.dto.response.DefaultIdResponse;
import cotato.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<DataResponse<DefaultIdResponse>> registerAdmin(@RequestBody AdminRequest request) {
        Long id = adminService.registerAdmin(request);
        return ResponseEntity.ok(DataResponse.from(DefaultIdResponse.of(id)));
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<DataResponse<AdminResponse>> getAdmin(@PathVariable Long adminId) {
        AdminResponse response = adminService.getAdmin(adminId);
        return ResponseEntity.ok(DataResponse.from(response));
    }

    @PostMapping("/{adminId}")
    public ResponseEntity<DataResponse<Void>> updateAdmin(@PathVariable Long adminId,
                                                          @RequestBody AdminRequest request) {
        adminService.updateAdmin(adminId, request);
        return ResponseEntity.ok(DataResponse.ok());
    }
}
