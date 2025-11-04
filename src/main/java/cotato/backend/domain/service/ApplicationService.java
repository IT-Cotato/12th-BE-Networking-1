package cotato.backend.domain.service;

import lombok.RequiredArgsConstructor;
import cotato.backend.api.dto.application.ApplicationCreateRequest;
import cotato.backend.domain.entity.*;
import cotato.backend.domain.repository.ApplicationLikeRepository;
import cotato.backend.domain.repository.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final ApplicantService applicantService;
    private final ApplicationRepository applicationRepository;
    private final ApplicationLikeRepository likeRepository;
    private final AdminService adminService;

    @Transactional
    public Application create(ApplicationCreateRequest req) {
        var applicant = applicantService.upsertByPhone(req.name(), req.age(), req.phoneNumber());

        var app = Application.builder()
                .applicant(applicant)
                .name(req.name())
                .period(req.period())
                .age(req.age())
                .part(Part.fromKor(req.part()))
                .ability(req.ability())
                .passion(req.passion())
                .phoneNumberCache(req.phoneNumber())
                .applicationTime(LocalDateTime.parse(req.applicationTime(), FMT))
                .likeCount(0)
                .build();

        return applicationRepository.save(app);
    }

    @Transactional(readOnly = true)
    public Application get(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("서류를 찾을 수 없습니다. id=" + id));
    }

    @Transactional(readOnly = true)
    public Page<Application> list(String filterBy, Integer period, Pageable pageable) {
        return switch (filterBy) {
            case "likes" -> applicationRepository.findAllByOrderByLikeCountDescApplicationTimeDesc(pageable);
            case "gisu" -> {
                if (period == null) throw new IllegalArgumentException("gisu 필터에는 period 파라미터가 필요합니다.");
                yield applicationRepository.findByPeriodOrderByApplicationTimeDesc(period, pageable);
            }
            case "gisu+likes" -> {
                if (period == null) throw new IllegalArgumentException("gisu+likes 필터에는 period 파라미터가 필요합니다.");
                yield applicationRepository.findByPeriodOrderByLikeCountDescApplicationTimeDesc(period, pageable);
            }
            default -> throw new IllegalArgumentException("filterBy는 likes | gisu | gisu+likes 중 하나여야 합니다.");
        };
    }

    @Transactional
    public void like(Long applicationId, Long adminId) {
        if (likeRepository.existsByApplicationIdAndAdminId(applicationId, adminId)) {
            return; // 중복 좋아요 방지
        }
        var app = get(applicationId);
        var admin = adminService.get(adminId);

        likeRepository.save(ApplicationLike.builder()
                .application(app)
                .admin(admin)
                .build());

        int cnt = (int) likeRepository.countByApplicationId(applicationId);
        try {
            var f = Application.class.getDeclaredField("likeCount");
            f.setAccessible(true);
            f.set(app, cnt);
        } catch (Exception ignore) {}
    }
}
