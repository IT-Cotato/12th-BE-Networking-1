package cotato.backend.service;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.Admin;
import cotato.backend.domain.Role;
import cotato.backend.dto.request.AdminRequest;
import cotato.backend.dto.response.AdminResponse;
import cotato.backend.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    // POST /api/admin
    public Long registerAdmin(AdminRequest request) {
        if (adminRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new AppException(ErrorCode.DUPLICATE_PHONE_NUMBER);
        }

        Admin admin = adminRepository.save(request.toEntity());
        return admin.getAdminId();
    }

    // GET /api/admin/{adminId}
    public AdminResponse getAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return new AdminResponse(admin);
    }

    // PUT /api/admin/{adminId}
    @Transactional
    public void updateAdmin(Long id, AdminRequest request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        adminRepository.findByPhoneNumber(request.getPhoneNumber())
                .filter(a -> !a.getAdminId().equals(id))
                .ifPresent(a -> {
                    throw new AppException(ErrorCode.DUPLICATE_PHONE_NUMBER);
                });

        admin.update(
                request.getName(),
                request.getAge(),
                request.getPhoneNumber(),
                Role.valueOf(request.getRole().toUpperCase())
        );
    }
}
