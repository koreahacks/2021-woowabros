package koreahacks.woowabros.uniconn.member.application;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void getAndSave() {
        Member member = Member.builder()
            .isVerified(false)
            .authCode("abc")
            .build();

        System.out.println(memberRepository.save(member).block());
        System.out.println(memberRepository.findFirstByAuthCode(member.getAuthCode()).doOnNext(Member::verify).flatMap(memberRepository::save).map(Member::isVerified).block());
        System.out.println(memberRepository.findFirstByAuthCode(member.getAuthCode()).map(Member::isVerified).block());
    }
}
