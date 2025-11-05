package cotato.backend.staff.service;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.staff.entity.Staff;
import cotato.backend.domain.staff.repository.StaffRepository;
import cotato.backend.staff.dto.StaffCreateRequest;
import cotato.backend.staff.dto.StaffResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    // 운영진 등록
    @Transactional
    public StaffResponse createStaff(StaffCreateRequest request) {
        Staff staff = Staff.builder()
                .name(request.getName())
                .age(request.getAge())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .build();

        Staff savedStaff = staffRepository.save(staff);
        return StaffResponse.from(savedStaff);
    }

    // 운영진 조회
    @Transactional(readOnly = true)
    public StaffResponse getStaff(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STAFF_NOT_FOUND));
        return StaffResponse.from(staff);
    }

    // 운영진 정보 수정
    @Transactional
    public StaffResponse updateStaff(Long staffId, StaffCreateRequest request) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STAFF_NOT_FOUND));

        staff.updateInfo(
                request.getName(),
                request.getAge(),
                request.getPhoneNumber(),
                request.getRole()
        );

        return StaffResponse.from(staff);
    }
}
