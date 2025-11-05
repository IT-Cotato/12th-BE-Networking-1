package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.entity.Applicant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    // 전화번호 기준으로 지원자를 생성하거나 업데이트(업서트)
    // 입력값 검증 수행
    public Long upsert(String name, int age, String phoneNumber) {
        if (name == null || name.length() < 2 || name.length() > 10) {
            throw new IllegalArgumentException("이름은 2~10자여야 합니다.");
        }
        if (age < 22 || age > 30) {
            throw new IllegalArgumentException("나이는 22 이상 30 이하여야 합니다.");
        }
        if (phoneNumber == null || !phoneNumber.matches("010\\d{8}")) {
            throw new IllegalArgumentException("휴대폰 번호는 010으로 시작하는 11자리여야 합니다.");
        }

        Applicant applicant = applicantRepository.findByPhoneNumber(phoneNumber)
                .map(a -> {
                    a.setName(name);
                    a.setAge(age);
                    a.setPhoneNumber(phoneNumber);
                    return a;
                })
                .orElseGet(() -> Applicant.builder()
                        .name(name)
                        .age(age)
                        .phoneNumber(phoneNumber)
                        .build());

        return applicantRepository.save(applicant).getId();
    }

    // 지원자를 ID로 조회, 없으면 NOT_FOUND 예외를 던짐
    public Applicant getById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
    }

    @Transactional
    // 지원자 정보 수정
    // 이름, 나이, 연락처 수정
    public void update(Long id, String name, Integer age, String phoneNumber) {
        Applicant applicant = getById(id);
        if (name != null) {
            if (name.length() < 2 || name.length() > 10) {
                throw new IllegalArgumentException("이름은 2~10자여야 합니다.");
            }
            applicant.setName(name);
        }
        if (age != null) {
            if (age < 22 || age > 30) {
                throw new IllegalArgumentException("나이는 22 이상 30 이하여야 합니다.");
            }
            applicant.setAge(age);
        }
        if (phoneNumber != null) {
            if (!phoneNumber.matches("010\\d{8}")) {
                throw new IllegalArgumentException("휴대폰 번호는 010으로 시작하는 11자리여야 합니다.");
            }
            applicant.setPhoneNumber(phoneNumber);
        }
    }
}


