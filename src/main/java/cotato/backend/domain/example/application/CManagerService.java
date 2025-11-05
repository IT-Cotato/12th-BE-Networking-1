package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dto.request.ManagerUpdateRequest;
import cotato.backend.domain.example.dto.response.ManagerResponse;
import cotato.backend.domain.example.entity.CManager;
import cotato.backend.domain.example.dao.CManagerRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CManagerService {
    private final CManagerRepository cManagerRepository;

    private CManager findManagerById(Long managerId) {
        return cManagerRepository.findById(managerId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MANAGER_NOT_FOUND));
    }

    // 운영진 정보 조회
    @Transactional(readOnly = true)
    public ManagerResponse getManagerDetail(Long managerId) {
        CManager manager = findManagerById(managerId);
        return ManagerResponse.of(manager);
    }

    // 운영진 정보 수정
    public ManagerResponse updateManager(Long managerId, ManagerUpdateRequest request) {
        CManager manager = findManagerById(managerId);
        manager.setRole(CManager.Role.fromKoreanName(request.getRole()));

        return ManagerResponse.of(manager);
    }
}
