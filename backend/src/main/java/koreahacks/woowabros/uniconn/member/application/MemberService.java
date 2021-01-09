package koreahacks.woowabros.uniconn.member.application;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;

import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.member.presentation.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final EmailService mailSender;
    private final MemberRepository memberRepository;

    public Mono<String> create(MemberCreateRequest request) {
        Member member = request.toMember();
        Mono<Member> save = memberRepository.save(member);
        mailSender.sendAuthEmail(request.getEmail(), member.getAuthCode());

        return save.map(Member::getId);
    }

    public Mono<Member> authorize(String authCode) {
        return memberRepository.findFirstByAuthCode(authCode)
            .doOnNext(Member::verify)
            .flatMap(memberRepository::save);
    }

    public Mono<Member> findById(String id) {
        return memberRepository.findById(id);
    }
}
