package cotato.backend.domain.staff;

import cotato.backend.domain.staff.dto.StaffResponse;
import cotato.backend.domain.staff.dto.StaffUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @GetMapping("/{staffId}")
    public ResponseEntity<StaffResponse> getStaffInfo(
            @PathVariable Long staffId
    ) {
        StaffResponse response = staffService.getStaffInfo(staffId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{staffId}")
    public ResponseEntity<StaffResponse> updateStaffInfo(
            @PathVariable Long staffId,
            @Valid @RequestBody StaffUpdateRequest request
    ) {
        // DTO의 Validation(@NotBlank 등)이 @Valid를 통해 검증됨
        StaffResponse response = staffService.updateStaffInfo(staffId, request);
        return ResponseEntity.ok(response);
    }
}