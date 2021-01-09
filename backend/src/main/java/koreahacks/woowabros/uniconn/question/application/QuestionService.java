package koreahacks.woowabros.uniconn.question.application;

import koreahacks.woowabros.uniconn.answer.application.AnswerService;
import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.question.domain.Question;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionCreateRequest;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionWithSelectedResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, AnswerService answerService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Mono<String> create(QuestionCreateRequest request) {
        Mono<Question> saved = questionRepository.save(request.toEntity());
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
}
