package cotato.backend.domain.example.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicationFormRepository;
import cotato.backend.domain.example.dto.request.ApplicationFormRequest;
import cotato.backend.domain.example.dto.response.ApplicationFormListResponse;
import cotato.backend.domain.example.dto.response.ApplicationFormResponse;
import cotato.backend.domain.example.entity.ApplicationForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationFormService {

    private final ApplicationFormRepository applicationFormRepository;

    @Transactional
    public Long create(ApplicationFormRequest req) {
        ApplicationForm form = ApplicationForm.builder()
                .name(req.getName())
                .period(req.getPeriod())
                .age(req.getAge())
                .part(req.getPart())
                .ability(req.getAbility())
                .passion(req.getPassion())
                .phoneNumber(req.getPhoneNumber())
                .applicationTime(req.getApplicationTime())
                .build();

        return applicationFormRepository.save(form).getId();
    }

    @Transactional
    public List<ApplicationFormListResponse> getFilteredForms(String filterBy, int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize);
        List<ApplicationForm> forms;

        switch (filterBy) {
            case "likes" -> forms = applicationFormRepository.findAllOrderByLikes(pageable);
            case "gisu+likes" -> forms = applicationFormRepository.findAllOrderByPeriodAndLikes(pageable);
            case "gisu" -> forms = applicationFormRepository.findAllOrderByPeriod(pageable);
            default -> throw new AppException(ErrorCode.INVALID_PARAMETER);

        }

        List<ApplicationFormListResponse> responses = new ArrayList<>();
        for (ApplicationForm form : forms) {
            responses.add(mapToListResponse(form));
        }

        return responses;
    }

    @Transactional
    public ApplicationFormResponse get(Long id) {
        ApplicationForm form = applicationFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        return mapToResponse(form);
    }

    private ApplicationFormResponse mapToResponse(ApplicationForm form) {
        return ApplicationFormResponse.builder()
                .id(form.getId())
                .name(form.getName())
                .period(form.getPeriod())
                .age(form.getAge())
                .part(form.getPart())
                .ability(form.getAbility())
                .passion(form.getPassion())
                .phoneNumber(form.getPhoneNumber())
                .applicationTime(form.getApplicationTime())
                .build();
    }

    private ApplicationFormListResponse mapToListResponse (ApplicationForm form) {
        return ApplicationFormListResponse.builder()
                .name(form.getName())
                .period(form.getPeriod())
                .part(form.getPart())
                .likeCount(form.countLikes())
                .build();
    }
}
