package koreahacks.woowabros.uniconn.member.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class MemberControllerTest {

    private WebTestClient webTestClient;

    @Autowired
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(memberController)
            .build();
    }

    @Test
    void sample() {
        webTestClient.delete()
            .uri("/api/members")
            .header("Authorization",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkcXJkMTIzNDVAZ21haWwuY29tIiwiaWF0IjoxNjEwMTgxMzY5LCJleHAiOjE2MTIzMjg4NTN9.CXAq3jUrJFyzlsYuPJ2girJPp6e7he1UmESXFaXxH-c")
            .exchange()
            .expectStatus()
            .isOk();
    }
}
