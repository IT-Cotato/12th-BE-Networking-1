package cotato.backend.staff.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.staff.dto.StaffCreateRequest;
import cotato.backend.staff.dto.StaffResponse;
import cotato.backend.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public DataResponse<StaffResponse> createStaff(@RequestBody StaffCreateRequest createRequest) {
        StaffResponse staffResponse = staffService.createStaff(createRequest);
        return DataResponse.from(staffResponse);
    }

    @GetMapping("/{staffId}")
    public DataResponse<StaffResponse> getStaff(@PathVariable Long staffId) {
        StaffResponse staffResponse = staffService.getStaff(staffId);
        return DataResponse.from(staffResponse);
    }

    @PutMapping("/{staffId}")
    public DataResponse<StaffResponse> updateStaff(
            @PathVariable Long staffId,
            @RequestBody StaffCreateRequest updateRequest)
    {
        StaffResponse staffResponse = staffService.updateStaff(staffId, updateRequest);
        return DataResponse.from(staffResponse);
    }
}
