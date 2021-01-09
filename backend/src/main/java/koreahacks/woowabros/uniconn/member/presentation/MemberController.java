package koreahacks.woowabros.uniconn.member.presentation;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RenderingResponse;
import org.springframework.web.reactive.function.server.ServerResponse;

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

    @GetMapping("/auth/{authCode}")
    public Mono<Member> auth(@PathVariable String authCode, ServerHttpResponse response) {
        memberService.authorize(authCode);
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/"));

        return memberService.findByAuthCode(authCode);
    }

}
