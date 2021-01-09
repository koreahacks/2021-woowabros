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
        answerRepository.saveAll(Arrays.asList(
                Answer.builder().questionId(question.getId()).build(),
                Answer.builder().questionId(question.getId()).build(),
                Answer.builder().questionId(question.getId()).build()
        )).blockFirst();

        Mono<QuestionAnswerResponse> result = questionService.findBy(question.getId());
        StepVerifier.create(result)
                .consumeNextWith(it -> assertAll(
                        () -> assertThat(it.getId()).isEqualTo(question.getId()),
                        () -> assertThat(it.getAnswers()).hasSize(3)
                )).verifyComplete();
    }
}