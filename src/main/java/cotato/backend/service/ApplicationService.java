package cotato.backend.service;

import cotato.backend.domain.Applicant;
import cotato.backend.domain.Application;
import cotato.backend.dto.request.ApplicationRequest;
import cotato.backend.dto.response.ApplicationListResponse;
import cotato.backend.dto.response.ApplicationResponse;
import cotato.backend.repository.ApplicantRepository;
import cotato.backend.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;

    // 등록
    @Transactional
    public Long createApplication(ApplicationRequest request) {
        // 지원자 확인
        Applicant applicant = applicantRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> applicantRepository.save(request.toEntityApplicant()));

        Application application = request.toEntity(applicant);
        Application saved = applicationRepository.save(application);
        return saved.getApplicationId();
    }

    // 상세조회
    public ApplicationResponse getApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 지원서를 찾을 수 없습니다."));
        return new ApplicationResponse(application);
    }

    public ApplicationListResponse getApplications(String filterBy, int page, int pageSize) {
        Sort sort;
        switch (filterBy.toLowerCase()) {
            case "likes" -> sort = Sort.by(Sort.Direction.DESC, "likesNum");
            case "period" -> sort = Sort.by(Sort.Direction.DESC, "period");
            case "period+likes" -> sort = Sort.by(Sort.Direction.DESC, "period")
                    .and(Sort.by(Sort.Direction.DESC, "likesNum"));
            default -> throw new IllegalArgumentException("filterBy 파라미터가 올바르지 않습니다.");
        }

        Page<Application> result = applicationRepository.findAll(PageRequest.of(page - 1, pageSize, sort));
        return new ApplicationListResponse(page, pageSize, filterBy, result);
    }

}
