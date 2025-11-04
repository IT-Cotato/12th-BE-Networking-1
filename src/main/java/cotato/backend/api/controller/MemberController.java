package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.member.application.MemberService;
import cotato.backend.domain.member.dto.request.MemberRequest;
import cotato.backend.domain.member.dto.request.RoleChangeRequest;
import cotato.backend.domain.member.dto.response.MemberResponse;
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

    @PutMapping("/{id}")
    public DataResponse<MemberResponse> update(@PathVariable Long id, @RequestBody MemberRequest request) {
        return DataResponse.from(memberService.update(id, request));
    }

    @GetMapping("/{id}")
    public DataResponse<MemberResponse> findById(@PathVariable Long id) {
        return DataResponse.from(memberService.findById(id));
    }
}
