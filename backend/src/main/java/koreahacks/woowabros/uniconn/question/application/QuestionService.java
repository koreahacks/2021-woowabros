package koreahacks.woowabros.uniconn.question.application;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.question.domain.Question;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionCreateRequest;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionWithSelectedResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;

    public Mono<String> create(QuestionCreateRequest request, Member member) {
        Mono<Question> saved = questionRepository.save(request.toEntity(member.getId()));
        return saved.map(Question::getId);
    }

    public Mono<QuestionAnswerResponse> findBy(String id) {
        return questionRepository.findById(id)
            .flatMap(question ->
                answerRepository.findByQuestionId(question.getId())
                    .collectList()
                    .map(answers ->
                        QuestionAnswerResponse.of(question, answers)
                    ));
    }

    public void delete(String id) {
        questionRepository.deleteById(id);
    }

    public Flux<QuestionWithSelectedResponse> findAll() {
        return questionRepository.findAll()
            .flatMap(question ->
                answerRepository.findByQuestionId(question.getId())
                    .filter(Answer::isSelected)
                    .count()
                    .map(selected -> QuestionWithSelectedResponse.from(question, selected > 0))
            );
    }

    public Flux<QuestionResponse> search(String query) {
        NativeSearchQuery buildQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.boolQuery()
                .must(QueryBuilders.multiMatchQuery(query, "content", "title"))).build();

        return reactiveElasticsearchOperations.search(buildQuery, QuestionResponse.class,
            IndexCoordinates.of("questions", "answers"))
            .map(SearchHit::getContent);
    }
}
