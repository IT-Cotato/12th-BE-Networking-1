package cotato.backend.likes.service;

import cotato.backend.appllication.entity.Application;
import cotato.backend.appllication.repository.ApplicationRepository;
import cotato.backend.likes.entity.Likes;
import cotato.backend.likes.repository.LikesRepository;
import cotato.backend.manager.entity.Manager;
import cotato.backend.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikesService {

    private final LikesRepository likesRepository;
    private final ApplicationRepository applicationRepository;
    private final ManagerRepository managerRepository;

    /**
     * 좋아요 추가
     */
    @Transactional
    public void addLike(Long applicationId, Long managerId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지원서입니다."));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운영진입니다."));

        if (likesRepository.existsByApplicationAndManager(application, manager)) {
            throw new IllegalStateException("이미 좋아요를 누른 지원서입니다.");
        }

        Likes likes = Likes.builder()
                .application(application)
                .manager(manager)
                .build();

        likesRepository.save(likes);
        application.increaseLikeCount(); // Application 엔티티 내부 메서드
    }

    /**
     * 좋아요 취소
     */
    @Transactional
    public void removeLike(Long applicationId, Long managerId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지원서입니다."));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운영진입니다."));

        Likes likes = likesRepository.findByApplicationAndManager(application, manager)
                .orElseThrow(() -> new IllegalStateException("좋아요를 누르지 않은 지원서입니다."));

        likesRepository.delete(likes);
        application.decreaseLikeCount(); // Application 엔티티 내부 메서드
    }
}
