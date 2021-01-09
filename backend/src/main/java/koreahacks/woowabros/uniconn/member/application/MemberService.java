package koreahacks.woowabros.uniconn.member.application;

import org.springframework.stereotype.Service;

import koreahacks.woowabros.uniconn.common.JwtTokenProvider;
import koreahacks.woowabros.uniconn.common.TokenProvider;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.member.presentation.AccessToken;
import koreahacks.woowabros.uniconn.member.presentation.LoginRequest;
import koreahacks.woowabros.uniconn.member.presentation.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final EmailService mailSender;
    private final MemberRepository memberRepository;
    private final TokenProvider jwtTokenProvider;

    public Mono<String> create(MemberCreateRequest request) {
        Member member = request.toMember();
        Mono<Member> save = memberRepository.save(member);
        mailSender.sendAuthEmail(request.getEmail(), member.getAuthCode());

        return save.map(Member::getId);
    }

    public void authorize(String authCode) {
        memberRepository.findFirstByAuthCode(authCode)
            .doOnNext(Member::verify)
            .flatMap(memberRepository::save);
    }

    public Mono<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    public Mono<Void> deleteById(Mono<String> id) {
        return memberRepository.deleteById(id);
    }

    public Mono<AccessToken> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        return memberRepository.findFirstByEmail(email)
            .map(member -> {
                if (member.isPasswordMatch(loginRequest.getPassword())) {
                    return jwtTokenProvider.createToken(email);
                }
                throw new IllegalArgumentException("비밀번호가 맞지 않습니다");
            });
    }

    public Mono<Member> findFirstByEmail(String email) {
        return memberRepository.findFirstByEmail(email);
    }
}
