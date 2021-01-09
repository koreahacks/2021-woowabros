package koreahacks.woowabros.uniconn.answer.presentation;

import koreahacks.woowabros.uniconn.answer.application.AnswerService;
import koreahacks.woowabros.uniconn.answer.domain.ReactionType;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerCreateRequest;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import koreahacks.woowabros.uniconn.common.LoginMember;
import koreahacks.woowabros.uniconn.member.domain.Member;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public Mono<String> create(@LoginMember Member member, AnswerCreateRequest request) {
        return answerService.create(request, member);
    }

    @GetMapping("/user/{userId}")
    public Mono<List<AnswerResponse>> findBy(@PathVariable String userId) {
        return answerService.findBy(userId);
    }

    @PostMapping("/{id}/reaction")
    public void reaction(@LoginMember Member member, @PathVariable String id, @RequestBody ReactionType type) {
        answerService.reaction(type, id, member);
    }

    @PostMapping("/{id}/select")
    public void select(@LoginMember Member member, @PathVariable String id) {
        answerService.select(id, member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        answerService.delete(id);
    }
}
