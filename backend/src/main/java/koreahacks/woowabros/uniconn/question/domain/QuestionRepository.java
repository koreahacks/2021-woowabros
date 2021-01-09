package koreahacks.woowabros.uniconn.question.domain;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;

public interface QuestionRepository extends ReactiveElasticsearchRepository<Question, String> {
    Flux<Question> findAllByUserId(String userId);
}
