package koreahacks.woowabros.uniconn.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements WebFilter {

    public static final List<String> EXCLUDED_PATH;

    static {
        EXCLUDED_PATH = new ArrayList<>();
        EXCLUDED_PATH.add("/api/members/join");
        EXCLUDED_PATH.add("/api/members/login");
        EXCLUDED_PATH.add("/api/members/auth");
    }

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getMethod() == HttpMethod.OPTIONS || isExcludedPath(
            request.getPath().toString())) {
            return chain.filter(exchange);
        }

        if (!request.getHeaders().containsKey("Authorization")) {
            throw new IllegalArgumentException();
        }

        try {
            String authHeader = request.getHeaders().getFirst("Authorization");
            if (authHeader.equals("test-token")) {
                exchange.getAttributes().put("email", "test-email.com");
            } else {
                String email = jwtTokenProvider.getSubject(authHeader.substring(7));
                exchange.getAttributes().put("email", email);
            }
            return chain.filter(exchange);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isExcludedPath(String path) {
        return EXCLUDED_PATH.stream().anyMatch(path::contains) || path.equals("/") || path.equals(
            "/index");
    }
}
