package koreahacks.woowabros.uniconn.member.application;

import koreahacks.woowabros.uniconn.common.AuthCodeGenerator;
import koreahacks.woowabros.uniconn.common.TokenProvider;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.member.presentation.dto.*;
import koreahacks.woowabros.uniconn.question.domain.QuestionRepository;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final EmailService mailSender;
    private final TokenProvider jwtTokenProvider;
    private final AuthCodeGenerator authCodeGenerator;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;

    public Mono<String> create(MemberCreateRequest request) {
        Member member = request.toMember(authCodeGenerator);
        return memberRepository.findByEmail(request.getEmail())
            .collectList()
            .flatMap(members -> {
                if (members.size()==0) {
                    mailSender.sendAuthEmail(request.getEmail(), member.getAuthCode());
                    return memberRepository.save(member);
                }
                return Mono.error(new IllegalArgumentException("중뵉된 이메일입니다"));
            })
            .map(Member::getId);
    }

    public Mono<String> authorize(String authCode) {
        return memberRepository.findFirstByAuthCode(authCode)
                .doOnNext(Member::verify)
                .flatMap(memberRepository::save)
                .map(Member::getId);
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
        Mono<MemberInfoResponse> memberInfoResponseMono = memberRepository.findById(member.getId())
            .flatMap(member1 ->
                questionRepository.findAllByUserId(member.getId())
                    .collectList()
                    .map(questions -> MemberInfoResponse.of(member1,
                        QuestionResponse.listOf(questions))));
        return memberInfoResponseMono;
    }

    public Mono<Void> delete(Member loginMember) {
        return memberRepository.deleteById(loginMember.getId());
    }

    public Mono<MemberResponse> findById(String id) {
        return memberRepository.findById(id)
                .map(MemberResponse::of);
    }

    public Mono<List<StatisticResponse>> calculateStatistic(String userId) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("userId", userId))
                        .must(QueryBuilders.matchQuery("isSelected", true)))
                .addAggregation(AggregationBuilders.terms("my_agg").field("major"))
                .build();

        return reactiveElasticsearchOperations.aggregate(query, StatisticResponse.class,
                IndexCoordinates.of("answers"))
                .next()
                .map(aggregation -> ((Terms) aggregation).getBuckets()
                        .stream()
                        .map(bucket -> new StatisticResponse(
                                (String) bucket.getKey(), (int) bucket.getDocCount()))
                        .collect(Collectors.toList()));
    }
}
