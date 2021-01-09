package koreahacks.woowabros.uniconn.answer.application;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.answer.domain.ReactionType;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerCreateRequest;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public AnswerService(AnswerRepository answerRepository, MemberRepository memberRepository) {
        this.answerRepository = answerRepository;
        this.memberRepository = memberRepository;
    }

    public Mono<String> create(AnswerCreateRequest request) {
        return answerRepository.save(request.toEntity())
                .map(Answer::getId);
    }

    public Mono<List<AnswerResponse>> findBy(String userId) {
        return answerRepository.findByUserId(userId)
                .map(AnswerResponse::of).collectList();
    }

    public Mono<Answer> reaction(ReactionType type, String answerId, Member member) {
        return answerRepository.findById(answerId)
                .doOnNext(answer -> answer.addReaction(type, member.getId()))
                .doOnNext(answerRepository::save);
    }

    public void delete(String id) {
        answerRepository.deleteById(id);
    }
}
