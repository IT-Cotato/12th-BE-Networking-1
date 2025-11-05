package cotato.backend.domain.staff.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.staff.dao.StaffRepository;
import cotato.backend.domain.staff.dto.request.StaffRequest;
import cotato.backend.domain.staff.dto.response.StaffResponse;
import cotato.backend.domain.staff.entity.StaffEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class StaffService {

    private final StaffRepository staffRepository;

    @Transactional
    public Long createStaff(StaffRequest request) {
        StaffEntity staff = request.toEntity();
        return staffRepository.save(staff).getId();
    }

    public StaffResponse getStaff(Long id) {
        return StaffResponse.from(
                staffRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STAFF_NOT_FOUND))
        );
    }

    @Transactional
    public StaffResponse updateStaff(Long id, StaffRequest request) {
        StaffEntity staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STAFF_NOT_FOUND));
        staff.update(
                request.name(),
                request.age(),
                request.phoneNumber(),
                request.role()
        );
        return StaffResponse.from(staff);
    }
}