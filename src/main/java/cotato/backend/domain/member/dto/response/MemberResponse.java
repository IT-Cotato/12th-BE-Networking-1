package cotato.backend.domain.member.dto.response;

import cotato.backend.common.enums.Part;
import cotato.backend.domain.member.entity.Member;
import cotato.backend.domain.member.enums.Role;

public record MemberResponse(
        String name,
        Integer generation,
        Integer age,
        Part part,
        String phoneNum,
        Role role
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getGeneration(), member.getAge(), member.getPart(), member.getPhoneNum(), member.getRole());
    }
}
