package koreahacks.woowabros.uniconn.answer.application;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerCreateRequest;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Mono<String> create(AnswerCreateRequest request) {
        return answerRepository.save(request.toEntity())
                .map(Answer::getId);
    }

    public Flux<AnswerResponse> findBy(String userId) {
        return answerRepository.findByUserId(userId)
                .map(AnswerResponse::of);
    }

    public void delete(String id) {
        answerRepository.deleteById(id);
    }
}
