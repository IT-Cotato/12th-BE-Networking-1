package cotato.backend.service;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
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

    // POST /api/application
    @Transactional
    public Long createApplication(ApplicationRequest request) {
        // 지원자 확인
        Applicant applicant = applicantRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> applicantRepository.save(request.toEntityApplicant()));

        Application application = request.toEntity(applicant);
        Application saved = applicationRepository.save(application);
        return saved.getApplicationId();
    }

    // GET /api/application/{applicationId}
    public ApplicationResponse getApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPLICATION_NOT_FOUND));
        return new ApplicationResponse(application);
    }

    // GET /api/application/list?filterBy={filter}}&page={pagenum}
    public ApplicationListResponse getApplications(String filterBy, int page, int pageSize) {
        if (page < 1) {
            throw new AppException(ErrorCode.INVALID_PAGE);
        }

        Pageable pageable;
        Page<Application> result;

        switch (filterBy.toLowerCase()) {
            case "likes" -> {
                pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "likesNum"));
                result = applicationRepository.findAll(pageable);
            }
            case "periodlikes" -> {
                pageable = PageRequest.of(page - 1, pageSize,
                        Sort.by(Sort.Direction.DESC, "period")
                                .and(Sort.by(Sort.Direction.DESC, "likesNum")));
                result = applicationRepository.findAll(pageable);
            }
            case "period" -> {
                pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "period"));
                result = applicationRepository.findAll(pageable);
            }
            default -> throw new AppException(ErrorCode.INVALID_FILTER);
        }

        if (result.isEmpty()) {
            throw new AppException(ErrorCode.APPLICATION_LIST_EMPTY);
        }

        return new ApplicationListResponse(page, pageSize, filterBy, result);
    }
}