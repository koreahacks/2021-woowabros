package koreahacks.woowabros.uniconn.member.application;

import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        System.out.println(memberRepository.findFirstByAuthCode(member.getAuthCode()).doOnNext(Member::verify).flatMap(memberRepository::save).map(Member::isVerified).block());
        System.out.println(memberRepository.findFirstByAuthCode(member.getAuthCode()).map(Member::isVerified).block());
    }
}
