package koreahacks.woowabros.uniconn.member.application;

import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import koreahacks.woowabros.uniconn.member.presentation.AccessToken;
import koreahacks.woowabros.uniconn.member.presentation.LoginRequest;
import reactor.test.StepVerifier;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll().block();
    }

    @Test
    void getAndSave() {
        Member member = Member.builder()
            .isVerified(false)
            .authCode("abc")
            .build();

        System.out.println(memberRepository.save(member).block());
        System.out.println(memberRepository.findFirstByAuthCode(member.getAuthCode())
            .doOnNext(Member::verify)
            .flatMap(memberRepository::save)
            .map(Member::isVerified)
            .block());
        System.out.println(memberRepository.findFirstByAuthCode(member.getAuthCode())
            .map(Member::isVerified)
            .block());
    }

    @Test
    void findFirstByEmail() {
        LoginRequest loginRequest = new LoginRequest("dqrd12345@gmail.com", "12345678");
        StepVerifier.create(
            memberRepository.findFirstByEmail(loginRequest.getEmail()).map(member->{
                if (member.isPasswordMatch(loginRequest.getPassword())) {
                    return new AccessToken("zz");
                }
                throw new IllegalArgumentException();
            }).map(AccessToken::getAccessToken))
            .expectNext("12345678")
            .verifyComplete();
    }
}
