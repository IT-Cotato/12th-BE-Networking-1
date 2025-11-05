package cotato.backend.domain.example.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.AdminRepository;
import cotato.backend.domain.example.dto.request.AdminRequest;
import cotato.backend.domain.example.dto.response.AdminResponse;
import cotato.backend.domain.example.entity.Admin;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public Long create(AdminRequest req) {
        if (adminRepository.existsByPhoneNumber(req.getPhoneNumber())) {
            throw new AppException(ErrorCode.INVALID_PARAMETER);
        }

        Admin admin = Admin.builder()
                .name(req.getName())
                .age(req.getAge())
                .phoneNumber(req.getPhoneNumber())
                .role(req.getRole())
                .build();
        return adminRepository.save(admin).getId();
    }

    @Transactional
    public AdminResponse get(Long id) {
        Admin admin = adminRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        return mapToResponse(admin);
    }

    @Transactional
    public AdminResponse update(Long id, AdminRequest req) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        admin.update(req.getName(), req.getAge(), req.getPhoneNumber(), req.getRole());
        return mapToResponse(admin);
    }

    private AdminResponse mapToResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .age(admin.getAge())
                .phoneNumber(admin.getPhoneNumber())
                .role(admin.getRole())
                .build();
    }
}
