package koreahacks.woowabros.uniconn.question.application;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.question.domain.Question;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        Question question = questionRepository.save(Question.builder().userId("dd").build()).block();
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
        Question question = questionRepository.save(Question.builder().userId("dd").build()).block();
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
        List<String> ids = questionRepository.saveAll(Arrays.asList(
                Question.builder().userId("dd").build(),
                Question.builder().userId("dd").build(),
                Question.builder().userId("dd").build()
        )).collectList()
                .block()
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
                .consumeNextWith(questionResponse -> assertThat(questionResponse.isSelected()).isFalse())
                .consumeNextWith(questionResponse -> assertThat(questionResponse.isSelected()).isTrue())
                .consumeNextWith(questionResponse -> assertThat(questionResponse.isSelected()).isFalse())
                .verifyComplete();
    }
}