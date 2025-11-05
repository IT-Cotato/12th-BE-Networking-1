package cotato.backend.domain.example.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.AdminRepository;
import cotato.backend.domain.example.dao.ApplicationFormRepository;
import cotato.backend.domain.example.dao.LikeRepository;
import cotato.backend.domain.example.dto.request.LikeRequest;
import cotato.backend.domain.example.entity.Admin;
import cotato.backend.domain.example.entity.ApplicationForm;
import cotato.backend.domain.example.entity.Like;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final AdminRepository adminRepository;
    private final ApplicationFormRepository applicationFormRepository;

    @Transactional
    public void likeForm(LikeRequest req) {

        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        ApplicationForm form = applicationFormRepository.findById(req.getFormId())
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        if (likeRepository.existsByAdminAndApplicationForm(admin, form))
            throw new AppException(ErrorCode.INVALID_PARAMETER);

        Like like = Like.builder()
                .admin(admin)
                .applicationForm(form)
                .build();

        likeRepository.save(like);
    }
}
