package cotato.backend.domain.example.application;

import cotato.backend.api.dto.request.ApplicationCreateRequest;
import cotato.backend.api.dto.response.ApplicationDetailResponse;
import cotato.backend.api.dto.response.ApplicationListItem;
import cotato.backend.domain.example.dao.*;
import cotato.backend.domain.example.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {
    private final ApplicantRepository applicantRepo;
    private final ApplicationRepository applicationRepo;
    private final ApplicationLikeRepository likeRepo;

    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Long create(ApplicationCreateRequest req) {
        // phone 기준 Applicant upsert
        Applicant applicant = applicantRepo.findByPhone(req.getPhoneNumber())
                .map(a -> { a.update(req.getName(), req.getAge(), req.getPhoneNumber()); return a; })
                .orElseGet(() -> applicantRepo.save(Applicant.builder()
                        .name(req.getName()).age(req.getAge()).phone(req.getPhoneNumber()).build()));

        LocalDateTime at;
        try { at = LocalDateTime.parse(req.getApplicationTime(), F); }
        catch (Exception e) { throw new ResponseStatusException(BAD_REQUEST, "applicationTime format must be yyyy-MM-dd HH:mm"); }

        Application app = Application.builder()
                .applicant(applicant)
                .period(req.getPeriod())
                .part(req.getPart())
                .ability(req.getAbility())
                .passion(req.getPassion())
                .phoneNumber(req.getPhoneNumber())
                .applicationTime(at)
                .build();

        return applicationRepo.save(app).getId();
    }

    @Transactional(readOnly = true)
    public ApplicationDetailResponse get(Long id) {
        Application a = applicationRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "application not found"));
        long likes = likeRepo.countByApplicationId(a.getId());
        return ApplicationDetailResponse.builder()
                .id(a.getId())
                .name(a.getApplicant().getName())
                .period(a.getPeriod())
                .age(a.getApplicant().getAge())
                .part(a.getPart())
                .ability(a.getAbility())
                .passion(a.getPassion())
                .phoneNumber(a.getPhoneNumber())
                .applicationTime(a.getApplicationTime())
                .likes(likes)
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ApplicationListItem> list(String filterBy, Integer period, Pageable pageable) {
        Page<Application> page;
        if ("likes".equalsIgnoreCase(filterBy)) {
            page = applicationRepo.findAllOrderByLikes(pageable);
        } else if ("gisu+likes".equalsIgnoreCase(filterBy)) {
            if (period == null) throw new ResponseStatusException(BAD_REQUEST, "period is required for gisu+likes");
            page = applicationRepo.findByPeriodOrderByLikes(period, pageable);
        } else { // gisu
            if (period == null) throw new ResponseStatusException(BAD_REQUEST, "period is required for gisu");
            page = applicationRepo.findByPeriod(period, pageable);
        }
        return page.map(a -> ApplicationListItem.builder()
                .id(a.getId())
                .name(a.getApplicant().getName())
                .period(a.getPeriod())
                .part(a.getPart())
                .likes(likeRepo.countByApplicationId(a.getId()))
                .build());
    }

    public void like(Long applicationId, Long staffId) {
        if (likeRepo.existsByApplicationIdAndStaffId(applicationId, staffId)) return; // 멱등
        Application app = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "application not found"));
        Staff staff = new Staff(); // 조회 최적화; 존재 보장하려면 repo로 검증해도 됨
        try {
            var f = Staff.class.getDeclaredField("id");
            f.setAccessible(true);
            f.set(staff, staffId);
        } catch (Exception ignored) {}
        likeRepo.save(ApplicationLike.builder().application(app).staff(staff).build());
    }
}
