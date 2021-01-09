package koreahacks.woowabros.uniconn.member.presentation;

import koreahacks.woowabros.uniconn.member.domain.Major;
import koreahacks.woowabros.uniconn.member.domain.MemberRepository;
import koreahacks.woowabros.uniconn.member.presentation.dto.LoginRequest;
import koreahacks.woowabros.uniconn.member.presentation.dto.MemberCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberControllerTest {

    private WebTestClient webTestClient;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll().block();
        webTestClient = WebTestClient.bindToController(memberController)
                .build();
    }

    @DisplayName("회원 가입")
    @Test
    void create() {
        MemberCreateRequest request = new MemberCreateRequest("abc@korea.ac.kr", "12345678", "dd",
                Major.COMPUTER_SCIENCE);

        webTestClient.post()
                .uri("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(body -> assertThat(body).isNotNull());
    }

    @DisplayName("로그인")
    @Test
    void login() {
        LoginRequest request = new LoginRequest("fucct@sogang.ac.kr", "12345678");

        webTestClient.post()
                .uri("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(body -> assertThat(body).isNotNull());
    }
}
