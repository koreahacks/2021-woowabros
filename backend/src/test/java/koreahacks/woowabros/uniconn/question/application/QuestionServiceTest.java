package koreahacks.woowabros.uniconn.question.application;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.question.domain.Question;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll().block();
        answerRepository.deleteAll().block();
    }

    @Test
    void findById() {
        Question question = questionRepository.save(Question.builder().userId("dd").build())
            .block();
        Answer selectedAnswer = answerRepository.saveAll(Arrays.asList(
            Answer.builder().questionId(question.getId()).isSelected(true).build(),
            Answer.builder().questionId(question.getId()).build(),
            Answer.builder().questionId(question.getId()).build()
        )).blockFirst();

        Mono<QuestionAnswerResponse> result = questionService.findBy(question.getId());
        StepVerifier.create(result)
            .consumeNextWith(it -> assertAll(
                () -> assertThat(it.getId()).isEqualTo(question.getId()),
                () -> assertThat(it.getAnswers()).hasSize(3),
                () -> assertThat(it.getSelectedCommentId()).isEqualTo(selectedAnswer.getId())
            )).verifyComplete();
    }

    @Test
    void findByIdNonSelected() {
        Question question = questionRepository.save(Question.builder().userId("dd").build())
            .block();
        answerRepository.saveAll(Arrays.asList(
            Answer.builder().questionId(question.getId()).build(),
            Answer.builder().questionId(question.getId()).build(),
            Answer.builder().questionId(question.getId()).build()
        )).blockFirst();

        Mono<QuestionAnswerResponse> result = questionService.findBy(question.getId());
        StepVerifier.create(result)
            .consumeNextWith(it -> assertAll(
                () -> assertThat(it.getId()).isEqualTo(question.getId()),
                () -> assertThat(it.getAnswers()).hasSize(3),
                () -> assertThat(it.getSelectedCommentId()).isNull()
            )).verifyComplete();
    }

    @Test
    void findAll() {
        List<String> ids = Objects.requireNonNull(questionRepository.saveAll(Arrays.asList(
            Question.builder().userId("dd").build(),
            Question.builder().userId("dd").build(),
            Question.builder().userId("dd").build()
        )).collectList()
            .block())
            .stream()
            .map(Question::getId)
            .collect(Collectors.toList());
        answerRepository.saveAll(Arrays.asList(
            Answer.builder().questionId(ids.get(0)).build(),
            Answer.builder().questionId(ids.get(0)).build(),
            Answer.builder().questionId(ids.get(1)).isSelected(true).build(),
            Answer.builder().questionId(ids.get(1)).build(),
            Answer.builder().questionId(ids.get(2)).build()
        )).blockFirst();

        StepVerifier.create(questionService.findAll())
            .consumeNextWith(
                questionResponse -> assertThat(questionResponse.isSelected()).isFalse())
            .consumeNextWith(questionResponse -> assertThat(questionResponse.isSelected()).isTrue())
            .consumeNextWith(
                questionResponse -> assertThat(questionResponse.isSelected()).isFalse())
            .verifyComplete();
    }

    @Test
    void search() {
        questionRepository.saveAll(Arrays.asList(
            Question.builder().userId("dd").title("미적분학이 너무 어려워요").build(),
            Question.builder().userId("lulu").content("수학이 너무 어렵네요 죽겠어요").build(),
            Question.builder().userId("coco").content("코딩이  너무 힘들어요").build()
        ))
            .collectList()
            .block();
        answerRepository.save(Answer.builder().userId("lala").content("저는 미술이 어렵던데").build()).block();
        StepVerifier.create(questionService.search("미적분학").map(QuestionResponse::getUserId))
            .expectNext("dd")
            .verifyComplete();

        StepVerifier.create(questionService.search("수학").map(QuestionResponse::getUserId))
            .expectNext("lulu")
            .verifyComplete();

        StepVerifier.create(questionService.search("코딩").map(QuestionResponse::getUserId))
            .expectNext("coco")
            .verifyComplete();
        StepVerifier.create(questionService.search("미술").map(QuestionResponse::getUserId))
            .expectNext("lala")
            .verifyComplete();
    }
}
