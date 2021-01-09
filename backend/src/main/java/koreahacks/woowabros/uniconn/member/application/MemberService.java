package koreahacks.woowabros.uniconn.member.application;

import javax.mail.MessagingException;

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

    public Mono<String> create(MemberCreateRequest request){
        Member member = request.toMember();
        Mono<Member> save = memberRepository.save(member);
        mailSender.sendAuthEmail(request.getEmail(), member.getAuthCode());

        return save.map(Member::getId);
    }
}
