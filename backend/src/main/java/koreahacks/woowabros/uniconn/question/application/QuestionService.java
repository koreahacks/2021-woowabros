package koreahacks.woowabros.uniconn.question.application;

import koreahacks.woowabros.uniconn.question.domain.Question;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionCommentResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionCreateRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Mono<String> create(QuestionCreateRequest request) {
        Mono<Question> saved = questionRepository.save(request.toEntity());
        return saved.map(Question::getId);
    }

    public Mono<QuestionCommentResponse> find(String id) {
        Mono<Question> question = questionRepository.findById(id);
        return question.map(QuestionCommentResponse::of);
    }

    public void delete(String id) {
        questionRepository.deleteById(id);
    }
}
