package cotato.backend.domain.member.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.member.dao.MemberRepository;
import cotato.backend.domain.member.dto.MemberRequest;
import cotato.backend.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberRequest request) {

        validateMember(request);

        Member member = Member.builder()
                .name(request.name())
                .generation(request.generation())
                .age(request.age())
                .part(request.part())
                .phoneNum(request.phoneNum()).build();

        return memberRepository.save(member).getId();
    }

    private void validateMember(MemberRequest request) {
        if (request.name() == null) {
            throw new AppException(ErrorCode.MEMBER_NAME_REQUIRED);
        }
        if (request.age() == null) {
            throw new AppException(ErrorCode.MEMBER_AGE_REQUIRED);
        }
        if (request.generation() == null) {
            throw new AppException(ErrorCode.MEMBER_GENERATION_REQUIRED);
        }
        if (request.part() == null) {
            throw new AppException(ErrorCode.MEMBER_PART_REQUIRED);
        }
        if (request.phoneNum() == null) {
            throw new AppException(ErrorCode.MEMBER_PHONE_NUM_REQUIRED);
        }

        if (request.name().length() < 2 || request.name().length() > 10) {
            throw new AppException(ErrorCode.MEMBER_INVALID_NAME_LENGTH);
        }
        if (request.age() < 22 || request.age() > 30) {
            throw new AppException(ErrorCode.MEMBER_INVALID_AGE_RANGE);
        }
        if (request.generation() < 1) {
            throw new AppException(ErrorCode.MEMBER_INVALID_GENERATION);
        }
        if (request.phoneNum().length() != 11) {
            throw new AppException(ErrorCode.MEMBER_INVALID_PHONE_NUM);
        }

        if (memberRepository.findByPhoneNum(request.phoneNum()).isPresent()) {
            throw new AppException(ErrorCode.MEMBER_DUPLICATE_PHONE_NUM);
        }
    }
}
