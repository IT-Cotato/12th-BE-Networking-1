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
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new IllegalArgumentException("지원서 없음."));
        return new ApplicationResponse(application);
    }

    public ApplicationListResponse getApplications(String filterBy, int page, int pageSize) {
        if (page < 1) {
            throw new IllegalArgumentException("페이지 번호는 1 이상이어야 합니다.");
        }

        Pageable pageable;
        Page<Application> result;

        switch (filterBy.toLowerCase()) {
            // 1. 좋아요 내림차순
            case "likes" -> {
                pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "likesNum"));
                result = applicationRepository.findAll(pageable);
            }
            case "periodlikes" -> {
                // 2. 기수+좋아요 내림차순
                pageable = PageRequest.of(page - 1, pageSize,
                        Sort.by(Sort.Direction.DESC, "period")
                                .and(Sort.by(Sort.Direction.DESC, "likesNum")));
                result = applicationRepository.findAll(pageable);
            }
            case "period" -> {
                // 3. 디폴트 기수 내림차순
                pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "period"));
                result = applicationRepository.findAll(pageable);
            }
            default -> throw new IllegalArgumentException("filterBy 파라미터 오류: likes, period, period+likes 중 하나여야 합니다.");
        }

        if (result.isEmpty()) {
            throw new IllegalStateException("검색 결과가 없습니다.");
        }

        return new ApplicationListResponse(page, pageSize, filterBy, result);
    }

}
