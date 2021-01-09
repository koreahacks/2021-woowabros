package koreahacks.woowabros.uniconn.answer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;

@SpringBootTest
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        answerRepository.deleteAll().blockOptional();
    }

    @Test
    void findByUserId() {
        answerRepository.saveAll(Arrays.asList(
                Answer.builder().userId("dd").build(),
                Answer.builder().userId("dd").build(),
                Answer.builder().userId("aa").build(),
                Answer.builder().userId("bb").build()
        )).blockFirst();

        Flux<Answer> result = answerRepository.findByUserId("dd");
        StepVerifier.create(result)
                .expectNextCount(2)
                .verifyComplete();
    }
}