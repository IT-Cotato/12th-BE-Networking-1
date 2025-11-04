package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.member.application.MemberService;
import cotato.backend.domain.member.dto.MemberRequest;
import cotato.backend.domain.member.dto.RoleChangeRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public DataResponse<DefaultIdResponse> save(@RequestBody MemberRequest request) {
        return DataResponse.created(DefaultIdResponse.of(memberService.save(request)));
    }

    @PatchMapping("/{id}/role")
    public DataResponse<Void> changeRole(@PathVariable Long id, @RequestBody RoleChangeRequest request) {
        memberService.updateRole(id, request);
        return DataResponse.ok();
    }
}
