package koreahacks.woowabros.uniconn.member.application;

import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.stereotype.Service;

import koreahacks.woowabros.uniconn.answer.domain.AnswerRepository;
import koreahacks.woowabros.uniconn.common.AuthCodeGenerator;
import koreahacks.woowabros.uniconn.common.TokenProvider;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.member.presentation.AccessToken;
import koreahacks.woowabros.uniconn.member.presentation.LoginRequest;
import koreahacks.woowabros.uniconn.member.presentation.MemberCreateRequest;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberInfoResponse;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final EmailService mailSender;
    private final TokenProvider jwtTokenProvider;
    private final AuthCodeGenerator authCodeGenerator;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ReactiveElasticsearchOperations operations;

    public Mono<String> create(MemberCreateRequest request) {
        Member member = request.toMember(authCodeGenerator);
        Mono<Member> save = memberRepository.save(member);
        mailSender.sendAuthEmail(request.getEmail(), member.getAuthCode());

        return save.map(Member::getId);
    }

    public void authorize(String authCode) {
        memberRepository.findFirstByAuthCode(authCode)
            .doOnNext(Member::verify)
            .flatMap(memberRepository::save);
    }

    public Mono<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    public Mono<AccessToken> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        return memberRepository.findFirstByEmail(email)
            .map(member -> {
                if (member.isPasswordMatch(loginRequest.getPassword())) {
                    return jwtTokenProvider.createToken(email);
                }
                throw new IllegalArgumentException("비밀번호가 맞지 않습니다");
            });
    }

    public Mono<Member> findFirstByEmail(String email) {
        return memberRepository.findFirstByEmail(email);
    }

    public Mono<MemberInfoResponse> findMemberInfo(Member member) {
        return memberRepository.findById(member.getId())
            .flatMap(member1 ->
                questionRepository.findAllByUserId(member.getId())
                    .collectList()
                    .map(questions -> MemberInfoResponse.of(member1, QuestionResponse.listOf(questions))));
    }

    public Flux<Member> findAll() {
        return memberRepository.findAll();
    }
}
