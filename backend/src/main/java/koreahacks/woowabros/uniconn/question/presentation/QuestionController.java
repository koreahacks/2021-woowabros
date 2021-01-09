package koreahacks.woowabros.uniconn.question.presentation;

import koreahacks.woowabros.uniconn.question.application.QuestionService;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionCreateRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Mono<String> create(@RequestBody QuestionCreateRequest request) {
        return questionService.create(request);
    }

    @GetMapping("/{id}")
    public Mono<QuestionAnswerResponse> find(@PathVariable String id) {
        return questionService.findBy(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        questionService.delete(id);
    }
}
