package koreahacks.woowabros.uniconn.question.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import koreahacks.woowabros.uniconn.common.LoginMember;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.question.application.QuestionService;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionCreateRequest;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionWithSelectedResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Mono<String> create(@LoginMember Member member,
        @RequestBody QuestionCreateRequest request) {
        return questionService.create(request, member);
    }

    @GetMapping("/{id}")
    public Mono<QuestionAnswerResponse> find(@PathVariable String id) {
        return questionService.findBy(id);
    }

    @GetMapping
    public Flux<QuestionWithSelectedResponse> findAll() {
        return questionService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        questionService.delete(id);
    }

    @GetMapping("/search")
    public Flux<QuestionResponse> search(@RequestParam String query) {
        return questionService.search(query);
    }
}
