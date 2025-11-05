package cotato.backend.service;

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

    public Long registerAdmin(AdminRequest request) {
        if (adminRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException("이미 등록된 전화번호입니다.");
        }

        Admin admin = adminRepository.save(request.toEntity());
        return admin.getAdminId();
    }

    public AdminResponse getAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("운영진 정보를 찾을 수 없습니다."));
        return new AdminResponse(admin);
    }

    @Transactional
    public void updateAdmin(Long id, AdminRequest request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("운영진 정보를 찾을 수 없습니다."));

        admin.update(request.getName(), request.getAge(), request.getPhoneNumber(), Role.valueOf(request.getRole().toUpperCase()));
    }
}
