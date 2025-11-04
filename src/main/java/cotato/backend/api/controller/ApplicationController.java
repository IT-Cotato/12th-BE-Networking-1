package cotato.backend.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import cotato.backend.api.dto.application.*;
import cotato.backend.domain.entity.Application;
import cotato.backend.domain.service.ApplicationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationDetailResponse create(@RequestBody @Valid ApplicationCreateRequest req) {
        Application app = applicationService.create(req);
        return toDetail(app);
    }

    @GetMapping("/{id}")
    public ApplicationDetailResponse get(@PathVariable Long id) {
        return toDetail(applicationService.get(id));
    }

    @GetMapping("/list")
    public ApplicationListResponse list(
            @RequestParam String filterBy,
            @RequestParam(required = false) Integer period,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;
        if (pageSize > 50) pageSize = 50;

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        var result = applicationService.list(filterBy, period, pageable);

        var rows = result.getContent().stream()
                .map(a -> new ApplicationListResponse.Row(
                        a.getId(),
                        a.getName(),
                        a.getPeriod(),
                        a.getPart().kor(),
                        a.getLikeCount()
                ))
                .toList();

        return new ApplicationListResponse(
                page, pageSize,
                result.getTotalElements(),
                result.getTotalPages(),
                rows
        );
    }

    // 좋아요: 서류 id + 운영진 id
    @PostMapping("/{id}/likes")
    public void like(@PathVariable Long id, @RequestBody @Valid LikeRequest req) {
        applicationService.like(id, req.adminId());
    }

    private static ApplicationDetailResponse toDetail(Application a) {
        return new ApplicationDetailResponse(
                a.getId(),
                a.getName(),
                a.getPeriod(),
                a.getAge(),
                a.getPart().kor(),
                a.getAbility(),
                a.getPassion(),
                a.getPhoneNumberCache(),
                a.getApplicationTime().toString().replace('T', ' '),
                a.getLikeCount()
        );
    }
}
