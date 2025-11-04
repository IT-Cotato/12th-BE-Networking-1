package cotato.backend.domain.member.dto.response;

import cotato.backend.common.enums.Part;
import cotato.backend.domain.member.entity.Member;

public record MemberResponse(
        String name,
        Integer generation,
        Integer age,
        Part part,
        String phoneNum
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getGeneration(), member.getAge(), member.getPart(), member.getPhoneNum());
    }
}
