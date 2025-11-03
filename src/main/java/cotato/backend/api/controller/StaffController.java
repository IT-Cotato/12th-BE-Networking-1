package cotato.backend.api.controller;

import cotato.backend.domain.staff.application.StaffService;
import cotato.backend.domain.staff.dto.request.StaffRequest;
import cotato.backend.domain.staff.dto.response.StaffResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/staff")
public class StaffController {

	private final StaffService staffService;

	@PostMapping
	public ResponseEntity<DataResponse<DefaultIdResponse>> save(@RequestBody StaffRequest request) {
		Long id = staffService.createStaff(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
			DataResponse.created(DefaultIdResponse.of(id))
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<StaffResponse>> getStaff(@PathVariable Long id) {
        StaffResponse response = staffService.getStaff(id);
        return ResponseEntity.ok(DataResponse.from(response));
	}

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<StaffResponse>> updateStaff(@PathVariable Long id, @RequestBody StaffRequest request) {
        StaffResponse response = staffService.updateStaff(id, request);
        return ResponseEntity.ok(DataResponse.from(response));
    }

}