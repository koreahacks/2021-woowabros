package koreahacks.woowabros.uniconn.question.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void save() {
        Question question = Question.builder().build();

        Mono<Question> save = questionRepository.save(question);
        StepVerifier.create(save).consumeNextWith(question1 ->
                assertAll(
                        () -> assertThat(question1.getId()).isEqualTo(question.getId()),
                        () -> assertThat(question1.getCreatedAt()).isNotNull()
                )).verifyComplete();
    }
}