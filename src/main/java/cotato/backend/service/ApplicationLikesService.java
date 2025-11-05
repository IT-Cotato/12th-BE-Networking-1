package cotato.backend.service;


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

    // 좋아요 등록
    @Transactional
    public void likeApplication(ApplicationLikesRequest request) {
        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new IllegalArgumentException("지원서를 찾을 수 없습니다."));
        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("운영진을 찾을 수 없습니다."));

        // 중복 좋아요 방지
        boolean exists = likesRepository.existsByApplicationAndAdmin(application, admin);
        if (exists) {
            throw new IllegalStateException("이미 좋아요를 누른 지원서입니다.");
        }

        ApplicationLikes like = ApplicationLikes.builder()
                .application(application)
                .admin(admin)
                .build();

        likesRepository.save(like);

        // 좋아요 수 업데이트
        application.increaseLikes();
    }

    // 지원서별 좋아요 조회
    public ApplicationLikesListResponse getLikes(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("지원서를 찾을 수 없습니다."));

        List<ApplicationLikes> likes = likesRepository.findByApplication(application);
        return new ApplicationLikesListResponse(applicationId, likes);
    }
}
