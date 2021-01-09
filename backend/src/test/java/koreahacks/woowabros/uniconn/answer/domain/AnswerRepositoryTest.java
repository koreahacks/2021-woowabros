package koreahacks.woowabros.uniconn.answer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        answerRepository.deleteAll().block();
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

    @Test
    void saveNested() {
        Answer answer = Answer.builder().build();
        answer.addReaction(ReactionType.LIKE, "dd");
        answer.addReaction(ReactionType.LIKE, "cc");
        answer.addReaction(ReactionType.DISLIKE, "bb");
        answerRepository.save(answer).block();

        StepVerifier.create(answerRepository.findById(answer.getId()))
                .consumeNextWith(it ->
                        assertThat(it.getReactions())
                                .extracting(Reaction::getUserId)
                                .isEqualTo(Arrays.asList("dd", "cc", "bb"))
                )
                .verifyComplete();

    }

    @Test
    void updateNested() {
        Answer answer = Answer.builder().build();
        answer.addReaction(ReactionType.LIKE, "dd");
        answer.addReaction(ReactionType.LIKE, "cc");
        answer.addReaction(ReactionType.DISLIKE, "bb");
        answerRepository.save(answer).block();

        answer.addReaction(ReactionType.LIKE, "bb");
        answer.addReaction(ReactionType.LIKE, "aa");
        answerRepository.save(answer).block();

        StepVerifier.create(answerRepository.findById(answer.getId()))
                .consumeNextWith(it ->
                        assertAll(
                                () -> assertThat(it.getReactions())
                                        .extracting(Reaction::getUserId)
                                        .isEqualTo(Arrays.asList("dd", "cc", "bb", "aa")),
                                () -> assertThat(it.getReactions())
                                        .extracting(Reaction::getType)
                                        .containsOnly(ReactionType.LIKE)
                        )

                )
                .verifyComplete();

    }
}