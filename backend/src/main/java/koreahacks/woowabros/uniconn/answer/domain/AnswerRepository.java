package koreahacks.woowabros.uniconn.answer.domain;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface AnswerRepository extends ReactiveElasticsearchRepository<Answer, String> {
}
