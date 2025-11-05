package cotato.backend.domain.example.application;

import cotato.backend.api.dto.request.StaffUpsertRequest;
import cotato.backend.domain.example.dao.StaffRepository;
import cotato.backend.domain.example.entity.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class StaffService {
    private final StaffRepository repo;

    public Long create(StaffUpsertRequest req) {
        return repo.save(Staff.builder()
                .name(req.getName()).age(req.getAge()).phone(req.getPhone()).role(req.getRole()).build()).getId();
    }

    public Long update(Long id, StaffUpsertRequest req) {
        Staff s = repo.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "staff not found"));
        s.update(req.getName(), req.getAge(), req.getPhone(), req.getRole());
        return s.getId();
    }

    @Transactional(readOnly = true)
    public StaffUpsertRequest get(Long id) {
        Staff s = repo.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "staff not found"));
        var dto = new StaffUpsertRequest();
        dto.setName(s.getName()); dto.setAge(s.getAge()); dto.setPhone(s.getPhone()); dto.setRole(s.getRole());
        return dto;
    }
}
