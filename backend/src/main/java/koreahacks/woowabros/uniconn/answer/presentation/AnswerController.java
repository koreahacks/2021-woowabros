package koreahacks.woowabros.uniconn.answer.presentation;

import koreahacks.woowabros.uniconn.answer.application.AnswerService;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerCreateRequest;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Mono<List<AnswerResponse>> findBy(@PathVariable String userId) {
        return answerService.findBy(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        answerService.delete(id);
    }
}
