package koreahacks.woowabros.uniconn.member.application;

import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.member.domain.Major;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import reactor.test.StepVerifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AnswerRepository answerRepository;

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
    void verify() {
        Member member = memberRepository.save(Member.builder()
            .nickname("dd")
            .isVerified(false)
            .authCode("dd")
            .build()
        ).block();

        assert member != null;
        StepVerifier.create(memberService.authorize("dd"))
            .expectNext(member.getId())
            .verifyComplete();
        StepVerifier.create(memberRepository.findById(member.getId())
            .map(Member::isVerified))
            .expectNext(true)
            .verifyComplete();

    }

    @Test
    void save() {
        memberRepository.save(Member.builder()
                .major(Major.COMPUTER_SCIENCE)
                .build())
                .block();
    }
}
