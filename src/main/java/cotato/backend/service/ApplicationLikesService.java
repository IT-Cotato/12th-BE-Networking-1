package cotato.backend.service;


import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.Admin;
import cotato.backend.domain.Application;
import cotato.backend.domain.ApplicationLikes;
import cotato.backend.dto.request.ApplicationLikesRequest;
import cotato.backend.dto.response.ApplicationLikesListResponse;
import cotato.backend.repository.AdminRepository;
import cotato.backend.repository.ApplicationLikesRepository;
import cotato.backend.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationLikesService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationLikesRepository likesRepository;
    private final AdminRepository adminRepository;

    // POST /api/applications/{applicationId}/likes
    @Transactional
    public void likeApplication(ApplicationLikesRequest request) {
        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new AppException(ErrorCode.APPLICATION_NOT_FOUND));

        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        if (likesRepository.existsByApplicationAndAdmin(application, admin)) {
            throw new AppException(ErrorCode.ALREADY_LIKED);
        }

        ApplicationLikes like = ApplicationLikes.builder()
                .application(application)
                .admin(admin)
                .build();

        likesRepository.save(like);
        application.increaseLikes();
    }

    // POST /api/applications/{applicationId}/likes
    @Transactional
    public ApplicationLikesListResponse getLikes(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new AppException(ErrorCode.APPLICATION_NOT_FOUND));

        List<ApplicationLikes> likes = likesRepository.findByApplication(application);
        return new ApplicationLikesListResponse(applicationId, likes);
    }
}
