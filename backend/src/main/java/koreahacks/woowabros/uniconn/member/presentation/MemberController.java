package koreahacks.woowabros.uniconn.member.presentation;

import koreahacks.woowabros.uniconn.common.LoginMember;
import koreahacks.woowabros.uniconn.member.application.MemberService;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.presentation.dto.AccessToken;
import koreahacks.woowabros.uniconn.member.presentation.dto.LoginRequest;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberCreateRequest;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberResponse;
import koreahacks.woowabros.uniconn.member.presentation.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/join")
    public Mono<String> create(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.create(request);
    }

    @PostMapping("/login")
    public Mono<AccessToken> getToken(@Valid @RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }

    @GetMapping("/auth/{authCode}")
    public ServerHttpResponse auth(@PathVariable String authCode, ServerHttpResponse response) {
        memberService.authorize(authCode);
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/"));

        return response;
    }

    @GetMapping
    public Mono<MemberResponse> retrieveSelf(@LoginMember Member member) {
        return Mono.just(MemberResponse.of(member));
    }

    @GetMapping("/{id}")
    public Mono<MemberResponse> retrieveById(@PathVariable String id) {
        return memberService.findById(id);
    }

    @GetMapping("/info")
    public Mono<MemberInfoResponse> retrieveInfoSelf(@LoginMember Member member) {
        return memberService.findMemberInfo(member);
    }

    @GetMapping("/info/{id}")
    public Mono<MemberInfoResponse> retrieveInfo(@PathVariable String id) {
        return memberService.findDetailById(id);
    }

    @DeleteMapping
    public Mono<Void> delete(@LoginMember Member loginMember) {
        return memberService.delete(loginMember);
    }

    @GetMapping("/statistic/{id}")
    public Mono<List<StatisticResponse>> calculateStatistic(@PathVariable String id) {
        return memberService.calculateStatistic(id);
    }
}
