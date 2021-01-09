package koreahacks.woowabros.uniconn.member.application;

import org.springframework.data.elasticsearch.core.ReactiveDocumentOperations;
import org.springframework.stereotype.Service;

import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.member.presentation.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final EmailService mailSender;
    private final MemberRepository memberRepository;
    private final ReactiveDocumentOperations documentOperations;

    public Mono<String> create(MemberCreateRequest request) {
        Member member = request.toMember();
        Mono<Member> save = memberRepository.save(member);
        mailSender.sendAuthEmail(request.getEmail(), member.getAuthCode());

        return save.map(Member::getId);
    }

    public void authorize(String authCode) {
        memberRepository.findByAuthCode(authCode)
            .doOnNext(member -> memberRepository.deleteById(member.getId()))
            .doOnNext(Member::verify)
            .doOnNext(memberRepository::save);
    }

    public Mono<Member> findByAuthCode(String authCode) {
        return memberRepository.findByAuthCode(authCode);
    }

}
