package com.ssafy.sai.domain.member.api;

import com.ssafy.sai.domain.member.dto.*;
import com.ssafy.sai.domain.member.service.MemberService;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    /**
     * @param id : 조회할 회원의 pk값
     * @return
     */
    @GetMapping("/member/{id}")
    public DataResponse<MemberDto> findMember(@PathVariable Long id) {
        MemberDto findMember = memberService.findMemberOne(id);
        return new DataResponse<>(200, "OK", findMember);
    }

    @PutMapping("/member/{id}")
    public MessageResponse updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequest request) throws Exception {
        memberService.updateMember(id, request);
        return new MessageResponse(200, "OK");
    }
}