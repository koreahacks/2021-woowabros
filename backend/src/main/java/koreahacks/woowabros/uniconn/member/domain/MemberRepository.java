package koreahacks.woowabros.uniconn.member.domain;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import reactor.core.publisher.Mono;

public interface MemberRepository extends ReactiveElasticsearchRepository<Member, String> {
    Mono<Member> findByAuthCode(String authCode);
}
