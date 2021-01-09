package koreahacks.woowabros.uniconn.answer.domain;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;

public interface AnswerRepository extends ReactiveElasticsearchRepository<Answer, String> {

    Flux<Answer> findByUserId(String userId);

    Flux<Answer> findByQuestionId(String questionId);
}
