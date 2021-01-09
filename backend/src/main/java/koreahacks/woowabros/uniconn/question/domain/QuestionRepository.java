package koreahacks.woowabros.uniconn.question.domain;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface QuestionRepository extends ReactiveElasticsearchRepository<Question, String> {
}
