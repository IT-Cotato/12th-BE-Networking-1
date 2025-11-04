package cotato.backend.manager.service;

import cotato.backend.manager.dto.ManagerCreateRequestDto;
import cotato.backend.manager.dto.ManagerResponseDto;
import cotato.backend.manager.dto.ManagerUpdateRequestDto;
import cotato.backend.manager.entity.Manager;
import cotato.backend.manager.enums.Role;
import cotato.backend.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;

    /**
     * 운영진 등록
     */
    @Transactional
    public ManagerResponseDto createManager(ManagerCreateRequestDto request) {
        Manager manager = Manager.builder()
                .name(request.getName())
                .birthYear(request.getBirthYear())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.fromString(request.getRole())) // 문자열 → Enum 변환
                .build();

        managerRepository.save(manager);

        return ManagerResponseDto.builder()
                .id(manager.getId())
                .name(manager.getName())
                .birthYear(manager.getBirthYear())
                .role(manager.getRole().getKorean()) // Enum → 한글 변환
                .phoneNumber(manager.getPhoneNumber())
                .build();
    }

    /**
     * 운영진 단건 조회
     */
    public ManagerResponseDto getManager(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운영진입니다."));

        return ManagerResponseDto.builder()
                .id(manager.getId())
                .name(manager.getName())
                .birthYear(manager.getBirthYear())
                .role(manager.getRole().getKorean())
                .phoneNumber(manager.getPhoneNumber())
                .build();
    }

    /**
     * 전체 운영진 조회
     */
    public List<ManagerResponseDto> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(m -> ManagerResponseDto.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .birthYear(m.getBirthYear())
                        .role(m.getRole().getKorean())
                        .phoneNumber(m.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 운영진 정보 수정
     */
    @Transactional
    public void updateManager(Long id, ManagerUpdateRequestDto request) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운영진입니다."));

        manager.updateManager(
                request.getName(),
                request.getPhoneNumber(),
                request.getBirthYear(),
                Role.fromString(request.getRole())
        );
    }

    /**
     * 운영진 삭제
     */
    @Transactional
    public void deleteManager(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운영진입니다."));
        managerRepository.delete(manager);
    }
}
