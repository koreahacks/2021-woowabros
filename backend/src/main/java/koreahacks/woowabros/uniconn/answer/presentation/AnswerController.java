package koreahacks.woowabros.uniconn.answer.presentation;

import koreahacks.woowabros.uniconn.answer.application.AnswerService;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerCreateRequest;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public Mono<String> create(AnswerCreateRequest request) {
        return answerService.create(request);
    }

    @GetMapping("/{userId}")
    public Flux<AnswerResponse> findBy(@PathVariable String userId) {
        return answerService.findBy(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        answerService.delete(id);
    }
}
