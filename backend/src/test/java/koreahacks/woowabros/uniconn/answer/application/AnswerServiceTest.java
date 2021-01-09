package koreahacks.woowabros.uniconn.answer.application;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.answer.domain.Reaction;
import koreahacks.woowabros.uniconn.answer.domain.ReactionType;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AnswerServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll().block();
        answerRepository.deleteAll().block();
    }

    @Test
    void reaction() {
        Member member = memberRepository.save(Member.builder().id("bb").build()).block();

        Answer answer = Answer.builder().userId(member.getId()).build();
        answer.addReaction(ReactionType.LIKE, "dd");
        answer.addReaction(ReactionType.LIKE, "cc");
        answer.addReaction(ReactionType.DISLIKE, "bb");
        answerRepository.save(answer).block();

        StepVerifier.create(answerService.reaction(ReactionType.LIKE, answer.getId(), member))
                .consumeNextWith(it -> assertThat(it.getReactions()).extracting(Reaction::getType).containsOnly(ReactionType.LIKE))
                .verifyComplete();

    }
}