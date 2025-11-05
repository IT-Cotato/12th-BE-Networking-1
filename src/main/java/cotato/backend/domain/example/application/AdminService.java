package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.AdminRepository;
import cotato.backend.domain.example.entity.Admin;
import cotato.backend.domain.example.entity.AdminRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    // 운영진 정보를 생성하거나 업데이트
    // 이름, 나이, 연락처, 역할
    public Long upsert(Long id, String name, Integer age, String phoneNumber, AdminRole role) {
        Admin admin = (id == null) ? new Admin() : getById(id);

        if (name != null) admin.setName(name);
        if (age != null) admin.setAge(age);
        if (phoneNumber != null) admin.setPhoneNumber(phoneNumber);
        if (role != null) admin.setRole(role);

        return adminRepository.save(admin).getId();
    }

    // 운영진을 ID로 조회, 없으면 NOT_FOUND 예외를 던짐
    public Admin getById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
    }
}


