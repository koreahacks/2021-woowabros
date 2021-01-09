package koreahacks.woowabros.uniconn.answer.application;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerCreateRequest;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

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

    public Mono<List<AnswerResponse>> findBy(String userId) {
        return answerRepository.findByUserId(userId)
                .map(AnswerResponse::of).collectList();
    }

    public void delete(String id) {
        answerRepository.deleteById(id);
    }
}
