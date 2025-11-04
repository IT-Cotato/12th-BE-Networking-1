package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dto.response.LikeListResponse;
import cotato.backend.domain.example.entity.Application;
import cotato.backend.domain.example.entity.Like;
import cotato.backend.domain.example.entity.LikeId;
import cotato.backend.domain.example.dao.ApplicationRepository;
import cotato.backend.domain.example.dao.LikeRepository;
import cotato.backend.domain.example.dao.CManagerRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final LikeRepository likeRepository;
    private final ApplicationRepository applicationRepository;
    private final CManagerRepository managerRepository;

    @Transactional
    public boolean toggleLike(Long applicationId, Long managerId) {
        // 지원서 엔티티 조회
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));

        // 운영진 id 존재 확인
        if (!managerRepository.existsById(managerId)) {
            throw new EntityNotFoundException(ErrorCode.MANAGER_NOT_FOUND);
        }

        LikeId likeId = new LikeId();
        likeId.setApplicationId(applicationId);
        likeId.setManagerId(managerId);

        // 좋아요 확인
        if (likeRepository.existsById(likeId)) { // 이 복합키가 DB에 존재하는지 확인
            // 좋아요 취소
            likeRepository.deleteById(likeId);
            application.setLikesCount(application.getLikesCount() - 1);
            return false;
        } else {
            // 좋아요 등록
            Like newLike = new Like();
            newLike.setId(likeId);
            newLike.setApplication(application);
            newLike.setCManager(managerRepository.getReferenceById(managerId));
            newLike.setCreatedAt(LocalDateTime.now());
            likeRepository.save(newLike);

            application.setLikesCount(application.getLikesCount() + 1);
            return true;
        }
    }

    // 좋아요 조회
    @Transactional(readOnly = true)
    public List<LikeListResponse> getLikesByApplication(Long applicationId) {
        List<Like> likes = likeRepository.findByApplication_ApplicationId(applicationId);

        return likes.stream()
                .map(LikeListResponse::of)
                .toList();
    }
}
