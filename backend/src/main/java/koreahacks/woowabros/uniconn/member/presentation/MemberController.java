package koreahacks.woowabros.uniconn.member.presentation;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreahacks.woowabros.uniconn.member.application.MemberService;
import koreahacks.woowabros.uniconn.member.domain.Member;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/")
    public Mono<String> create(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.create(request);
    }






}
