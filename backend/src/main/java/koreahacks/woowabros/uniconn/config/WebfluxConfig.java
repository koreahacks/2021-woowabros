package koreahacks.woowabros.uniconn.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import koreahacks.woowabros.uniconn.common.JwtAuthFilter;
import koreahacks.woowabros.uniconn.common.JwtTokenProvider;
import koreahacks.woowabros.uniconn.member.presentation.MemberController;

public class WebfluxConfig implements WebFluxConfigurer {
    // @Bean
    // public RouterFunction<ServerResponse> routeFilter(MemberController memberController) {
    //     return RouterFunctions.route(GET("/api/members/login")
    //         .and(accept(MediaType.APPLICATION_JSON)), memberController::getToken)
    //         .filter();
    // }
}
