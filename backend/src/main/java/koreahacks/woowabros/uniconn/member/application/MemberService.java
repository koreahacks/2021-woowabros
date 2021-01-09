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
import koreahacks.woowabros.uniconn.member.presentation.MemberResponse;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberInfoResponse;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
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

    public Mono<MemberInfoResponse> findDetailById(String id) {
        return memberRepository.findById(id).flatMap(member ->
            questionRepository.findAllByUserId(id)
                .collectList()
                .map(questions -> MemberInfoResponse.of(member,
                    QuestionResponse.listOf(questions))));
    }

    public Mono<AccessToken> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        return memberRepository.findFirstByEmail(email)
            .map(member -> {
                if (member.isPasswordMatch(loginRequest.getPassword())) {
                    if (!member.isVerified()) {
                        throw new IllegalArgumentException("아직 이메일 인증이 완료되지 않았습니다");
                    }
                    return jwtTokenProvider.createToken(email);
                } else {
                    throw new IllegalArgumentException("비밀번호가 맞지 않습니다");
                }
            }).onErrorStop();
    }

    public Mono<Member> findFirstByEmail(String email) {
        return memberRepository.findFirstByEmail(email);
    }

    public Mono<MemberInfoResponse> findMemberInfo(Member member) {
        return memberRepository.findById(member.getId())
            .flatMap(member1 ->
                questionRepository.findAllByUserId(member.getId())
                    .collectList()
                    .map(questions -> MemberInfoResponse.of(member1,
                        QuestionResponse.listOf(questions))));
    }

    public Mono<Void> delete(Member loginMember) {
        return memberRepository.deleteById(loginMember.getId());
    }

    public Mono<MemberResponse> findById(String id) {
        return memberRepository.findById(id)
            .map(MemberResponse::of);
    }
}
