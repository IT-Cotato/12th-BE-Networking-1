package cotato.backend.domain.service;

import cotato.backend.domain.entity.Admin;
import cotato.backend.domain.entity.AdminRole;
import cotato.backend.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public Admin create(String name, int age, String phoneNumber, String roleCode) {
        var admin = Admin.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .role(AdminRole.fromCode(roleCode))
                .build();
        return adminRepository.save(admin);
    }

    @Transactional(readOnly = true)
    public Admin get(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("운영진을 찾을 수 없습니다. id=" + id));
    }

    @Transactional
    public Admin update(Long id, String name, int age, String phoneNumber, String roleCode) {
        var a = get(id);
        a.setName(name);
        a.setAge(age);
        a.setPhoneNumber(phoneNumber);
        a.setRole(AdminRole.fromCode(roleCode));
        return a;
    }
}
