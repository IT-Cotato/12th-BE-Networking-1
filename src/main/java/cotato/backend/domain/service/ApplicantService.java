package cotato.backend.domain.service;

import lombok.RequiredArgsConstructor;
import cotato.backend.domain.entity.Applicant;
import cotato.backend.domain.repository.ApplicantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    public Applicant upsertByPhone(String name, int age, String phoneNumber) {
        return applicantRepository.findByPhoneNumber(phoneNumber)
                .map(a -> { a.setName(name); a.setAge(age); return a; })
                .orElseGet(() -> applicantRepository.save(
                        Applicant.builder()
                                .name(name)
                                .age(age)
                                .phoneNumber(phoneNumber)
                                .build()
                ));
    }

    @Transactional(readOnly = true)
    public Applicant getById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("지원자를 찾을 수 없습니다. id=" + id));
    }

    @Transactional
    public Applicant update(Long id, String name, int age, String phoneNumber) {
        var a = getById(id);
        a.setName(name);
        a.setAge(age);
        a.setPhoneNumber(phoneNumber);
        return a;
    }
}
