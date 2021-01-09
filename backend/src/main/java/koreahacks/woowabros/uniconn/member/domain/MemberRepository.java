package koreahacks.woowabros.uniconn.member.domain;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemberRepository extends ReactiveElasticsearchRepository<Member, String> {
    Mono<Member> findFirstByAuthCode(String authCode);

    Mono<Member> findFirstByEmail(String email);

    Flux<Member> findByEmail(String email);
}
