package cotato.backend.domain.example.application;

import cotato.backend.api.dto.request.ApplicantUpsertRequest;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.entity.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicantService {
    private final ApplicantRepository repo;

    public Long upsert(ApplicantUpsertRequest req) {
        Applicant a = repo.findByPhone(req.getPhone())
                .map(e -> { e.update(req.getName(), req.getAge(), req.getPhone()); return e; })
                .orElseGet(() -> repo.save(Applicant.builder()
                        .name(req.getName()).age(req.getAge()).phone(req.getPhone()).build()));
        return a.getId();
    }

    @Transactional(readOnly = true)
    public ApplicantUpsertRequest get(Long id) {
        Applicant a = repo.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "applicant not found"));
        var dto = new ApplicantUpsertRequest();
        dto.setName(a.getName()); dto.setAge(a.getAge()); dto.setPhone(a.getPhone());
        return dto;
    }
}
