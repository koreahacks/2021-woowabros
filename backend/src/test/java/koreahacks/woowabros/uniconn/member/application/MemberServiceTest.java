package koreahacks.woowabros.uniconn.member.application;

import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.member.domain.Major;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberCreateRequest;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberInfoResponse;
import koreahacks.woowabros.uniconn.question.domain.Question;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionResponse;
import reactor.test.StepVerifier;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private QuestionRepository questionRepository;

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
    void getMemberInfo() {
        Member member = memberRepository.save(Member.builder().nickname("dd").build())
            .block();
        // questionRepository.saveAll(Lists.newArrayList(Question.builder().userId("dd").build(),
        //     Question.builder().userId("dd").build(),
        //     Question.builder().userId("dd").build())
        // ).blockLast();
        assert member != null;
        MemberInfoResponse block = memberService.findMemberInfo(member).block();
        assert block != null;
        System.out.println(block.getId());
    }

    @Test
    void verifyDuplicate() {
        Member member = memberRepository.save(Member.builder()
            .nickname("dd")
            .isVerified(false)
            .authCode("dd")
            .email("dd@sogang.ac.kr")
            .build()
        ).block();
        StepVerifier.create(memberService.create(
            new MemberCreateRequest("dd@sogang.ac.kr", "12345678", "dd", Major.BUSINESS)))
            .expectError()
            .verify();
    }

    @Test
    void save() {
        memberRepository.save(Member.builder()
            .major(Major.COMPUTER_SCIENCE)
            .build())
            .block();
    }

    @Test
    void changeWork() {

    }
}
