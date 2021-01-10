package koreahacks.woowabros.uniconn.member.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import koreahacks.woowabros.uniconn.common.LoginMember;
import koreahacks.woowabros.uniconn.member.application.MemberService;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.presentation.dto.AccessToken;
import koreahacks.woowabros.uniconn.member.presentation.dto.LoginRequest;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberCreateRequest;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberInfoResponse;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberResponse;
import koreahacks.woowabros.uniconn.member.presentation.dto.StatisticResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Mono<String> create(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.create(request);
    }

    @PostMapping("/login")
    public Mono<AccessToken> getToken(@Valid @RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }

    @GetMapping("/auth")
    public Mono<String> auth(@RequestParam("code") String code) {
        return memberService.authorize(code);
    }

    @GetMapping
    public Mono<MemberResponse> retrieveSelf(@LoginMember Mono<Member> member) {
        return member.map(MemberResponse::of);
    }

    @GetMapping("/{id}")
    public Mono<MemberResponse> retrieveById(@PathVariable String id) {
        return memberService.findById(id);
    }

    @GetMapping("/info")
    public Mono<MemberInfoResponse> retrieveInfoSelf(@LoginMember Mono<Member> member) {
        return memberService.findMemberInfo(member);
    }

    @DeleteMapping
    public Mono<Void> delete(@LoginMember Mono<Member> loginMember) {
        return memberService.delete(loginMember);
    }

    @GetMapping("/statistic/{id}")
    public Mono<List<StatisticResponse>> calculateStatistic(@PathVariable String id) {
        return memberService.calculateStatistic(id);
    }
}
