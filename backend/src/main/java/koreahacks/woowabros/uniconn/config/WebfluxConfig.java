package koreahacks.woowabros.uniconn.config;

import org.springframework.web.reactive.config.WebFluxConfigurer;

public class WebfluxConfig implements WebFluxConfigurer {
    // @Bean
    // public RouterFunction<ServerResponse> routeFilter(MemberController memberController) {
    //     return RouterFunctions.route(GET("/api/members/login")
    //         .and(accept(MediaType.APPLICATION_JSON)), memberController::getToken)
    //         .filter();
    // }
}
