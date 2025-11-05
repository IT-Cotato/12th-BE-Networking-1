package cotato.backend.domain.like;

import cotato.backend.domain.application.Application;
import cotato.backend.domain.application.ApplicationRepository;
import cotato.backend.domain.staff.StaffDetail;
import cotato.backend.domain.staff.StaffDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ApplicationLikeService {

    private final ApplicationLikeRepository applicationLikeRepository;
    private final ApplicationRepository applicationRepository;
    private final StaffDetailRepository staffDetailRepository;


    @Transactional
    public void addLike(Long applicationId, Long staffId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NoSuchElementException("해당 지원서를 찾을 수 없습니다. ID: " + applicationId));

        StaffDetail staff = staffDetailRepository.findById(staffId)
                .orElseThrow(() -> new NoSuchElementException("운영진 계정이 아닙니다. ID: " + staffId));

        if (applicationLikeRepository.existsByApplicationIdAndStaffId(applicationId, staffId)) {
            throw new IllegalStateException("이미 좋아요를 누른 지원서입니다.");
        }

        ApplicationLike like = ApplicationLike.builder()
                .application(application)
                .staff(staff)
                .build();

        applicationLikeRepository.save(like);

        application.incrementLikeCount();
    }
}
