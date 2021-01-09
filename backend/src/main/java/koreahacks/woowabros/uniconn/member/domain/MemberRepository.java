package koreahacks.woowabros.uniconn.member.domain;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface MemberRepository extends ReactiveElasticsearchRepository<Member, String> {
}
