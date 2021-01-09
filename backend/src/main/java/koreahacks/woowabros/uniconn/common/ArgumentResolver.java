package koreahacks.woowabros.uniconn.common;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;

import koreahacks.woowabros.uniconn.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
        ServerWebExchange exchange) {
        try {
            String email = exchange.getAttributeOrDefault("email", "");

            return memberService.findFirstByEmail(email).map(member -> member);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
