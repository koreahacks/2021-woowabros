package koreahacks.woowabros.uniconn.member.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member member = Member.builder().id("dd").build();

        Mono<Member> save = memberRepository.save(member);
        StepVerifier.create(save).consumeNextWith(member1 ->
            assertThat(member1.getId()).isEqualTo(member.getId())
        ).verifyComplete();
    }
}
