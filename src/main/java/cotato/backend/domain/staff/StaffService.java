package cotato.backend.domain.staff;

import cotato.backend.domain.staff.dto.StaffResponse;
import cotato.backend.domain.staff.dto.StaffUpdateRequest;
import cotato.backend.domain.user.User;
import cotato.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffService {

    private final StaffDetailRepository staffDetailRepository;
    private final UserRepository userRepository;


    public StaffResponse getStaffInfo(Long staffId) {
        StaffDetail staffDetail = staffDetailRepository.findById(staffId)
                .orElseThrow(() -> new NoSuchElementException("해당 스태프를 찾을 수 없습니다. ID: " + staffId));

        return new StaffResponse(staffDetail);
    }

    @Transactional
    public StaffResponse updateStaffInfo(Long staffId, StaffUpdateRequest request) {
        StaffDetail staffDetail = staffDetailRepository.findById(staffId)
                .orElseThrow(() -> new NoSuchElementException("해당 스태프를 찾을 수 없습니다. ID: " + staffId));

        User user = staffDetail.getUser();
        user.updateProfile(request.getName(), request.getAge(), request.getPhoneNumber());

        StaffRole newRole = new StaffRoleConverter().convertToEntityAttribute(request.getRole());
        staffDetail.updateRole(newRole);

        return new StaffResponse(staffDetail);

    }
}
