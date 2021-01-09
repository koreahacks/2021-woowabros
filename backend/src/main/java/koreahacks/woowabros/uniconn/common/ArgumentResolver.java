package koreahacks.woowabros.uniconn.common;

import koreahacks.woowabros.uniconn.member.application.MemberService;
import koreahacks.woowabros.uniconn.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
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
    public Mono<Object> resolveArgument(
            MethodParameter parameter,
            BindingContext bindingContext,
            ServerWebExchange exchange
    ) {
        try {
            String email = exchange.getAttributeOrDefault("email", "");
            if ("test-email.com".equals(email)) {
                return Mono.just(Member.builder().id("test-id").isVerified(true).nickname("test-nickname").email("test-email.com").build());
            }
            return memberService.findFirstByEmail(email).map(member -> member);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
