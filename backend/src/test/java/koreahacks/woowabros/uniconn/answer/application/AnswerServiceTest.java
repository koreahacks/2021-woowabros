package koreahacks.woowabros.uniconn.answer.application;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.answer.domain.Reaction;
import koreahacks.woowabros.uniconn.answer.domain.ReactionType;
import koreahacks.woowabros.uniconn.exception.AlreadySelectedException;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.question.domain.Question;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AnswerServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll().block();
        answerRepository.deleteAll().block();
        questionRepository.deleteAll().block();
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

    @Test
    void select() {
        Member member = Member.builder().id("dd").build();
        Question question = Question.builder().userId(member.getId()).build();
        questionRepository.save(question).block();

        Answer answer1 = Answer.builder().questionId(question.getId()).userId(member.getId()).build();
        Answer answer2 = Answer.builder().questionId(question.getId()).userId("ee").build();
        Answer answer = answerRepository.saveAll(Arrays.asList(answer1, answer2)).blockFirst();

        StepVerifier.create(answerService.select(answer.getId(), member))
                .consumeNextWith(it -> assertThat(it.isSelected()).isTrue())
                .verifyComplete();
    }

    @Test
    void selectNotOwn() {
        Member member = Member.builder().id("dd").build();
        Question question = Question.builder().userId("not_dds").build();
        questionRepository.save(question).block();

        Answer answer = Answer.builder().questionId(question.getId()).userId(member.getId()).build();
        answerRepository.save(answer).block();

        StepVerifier.create(answerService.select(answer.getId(), member))
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    void selectAlreadySelected() {
        Member member = Member.builder().id("dd").build();
        Question question = Question.builder().userId(member.getId()).build();
        questionRepository.save(question).block();

        Answer answer1 = Answer.builder().questionId(question.getId()).isSelected(true).userId(member.getId()).build();
        Answer answer2 = Answer.builder().questionId(question.getId()).userId("ee").build();
        Answer answer = answerRepository.saveAll(Arrays.asList(answer1, answer2)).blockLast();

        StepVerifier.create(answerService.select(answer.getId(), member))
                .expectError(AlreadySelectedException.class)
                .verify();
    }
}
