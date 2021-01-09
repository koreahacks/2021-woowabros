package koreahacks.woowabros.uniconn.answer.application;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.answer.domain.ReactionType;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerCreateRequest;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import koreahacks.woowabros.uniconn.common.LoginMember;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository, MemberRepository memberRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public Mono<String> create(AnswerCreateRequest request, Member member) {
        return answerRepository.save(request.toEntity(member.getId()))
                .map(Answer::getId);
    }

    public Mono<List<AnswerResponse>> findBy(String userId) {
        return answerRepository.findByUserId(userId)
                .map(AnswerResponse::of).collectList();
    }

    public Mono<Answer> reaction(ReactionType type, String id, Member member) {
        return answerRepository.findById(id)
                .doOnNext(answer -> answer.addReaction(type, member.getId()))
                .doOnNext(answerRepository::save);
    }

    public Mono<Answer> select(String id, Member member) {
        return answerRepository.findById(id)
                .flatMap(answer -> questionRepository.findById(answer.getQuestionId()))
                .doOnNext(question -> question.verifyMember(member.getId()))
                .flatMapMany(question -> answerRepository.findByQuestionId(question.getId()))
                .doOnNext(Answer::verifyNotSelected)
                .filter(answer -> answer.getId().equals(id))
                .next()
                .doOnNext(Answer::select)
                .flatMap(answerRepository::save);
    }

    public void delete(String id) {
        answerRepository.deleteById(id);
    }
}
