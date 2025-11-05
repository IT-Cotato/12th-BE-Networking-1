package cotato.backend.domain.applicant.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicationFormLikeRepository;
import cotato.backend.domain.applicant.dao.ApplicationFormRepository;
import cotato.backend.domain.applicant.dto.response.ApplicationFormResponse;
import cotato.backend.domain.applicant.dto.response.LikeCountResponse;
import cotato.backend.domain.applicant.entity.ApplicationForm;
import cotato.backend.domain.applicant.entity.ApplicationFormLike;
import cotato.backend.domain.member.dao.MemberRepository;
import cotato.backend.domain.member.entity.Member;
import cotato.backend.domain.member.enums.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationFormLikeService {

    private final ApplicationFormLikeRepository applicationFormLikeRepository;
    private final MemberRepository memberRepository;
    private final ApplicationFormRepository applicationFormRepository;

    @Transactional
    public Long addLike(Long memberId, Long applicationFormId) {
        if (applicationFormLikeRepository.existsByMemberIdAndApplicationFormId(memberId, applicationFormId)) {
            throw new AppException(ErrorCode.LIKE_ALREADY_EXISTS);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));

        ApplicationForm applicationForm = applicationFormRepository.findById(applicationFormId)
                .orElseThrow(() -> new AppException(ErrorCode.APPLICATION_FORM_NOT_FOUND));

        if (member.getRole() == Role.MEMBER) {
            throw new AppException(ErrorCode.LIKE_ONLY_FOR_STAFF);
        }

        ApplicationFormLike applicationFormLike = ApplicationFormLike.builder()
                .member(member)
                .applicationForm(applicationForm)
                .build();
        return applicationFormLikeRepository.save(applicationFormLike).getId();
    }

    @Transactional
    public void deleteLike(Long memberId, Long applicationFormId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));

        if (member.getRole() == Role.MEMBER) {
            throw new AppException(ErrorCode.LIKE_ONLY_FOR_STAFF);
        }

        ApplicationFormLike applicationFormLike = applicationFormLikeRepository.findByMemberIdAndApplicationFormId(memberId, applicationFormId)
                .orElseThrow(() -> new AppException(ErrorCode.LIKE_NOT_FOUND));

        applicationFormLikeRepository.delete(applicationFormLike);
    }

    public LikeCountResponse getLikeCount(Long applicationFormId) {
        return LikeCountResponse.of(applicationFormLikeRepository.countByApplicationFormId(applicationFormId));
    }

    public List<ApplicationFormResponse> getMyLikedForms(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new AppException(ErrorCode.MEMBER_NOT_FOUND);
        }

        List<ApplicationFormLike> likes = applicationFormLikeRepository.findByMemberId(memberId);

        return likes.stream()
                .map(like -> ApplicationFormResponse.from(like.getApplicationForm()))
                .toList();
    }
}
