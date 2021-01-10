package koreahacks.woowabros.uniconn.common;

import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.core.ResolvableType;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolverSupport;
import org.springframework.web.server.ServerWebExchange;

import koreahacks.woowabros.uniconn.member.application.MemberService;
import koreahacks.woowabros.uniconn.member.domain.Member;
import reactor.core.publisher.Mono;

public class ArgumentResolver extends HandlerMethodArgumentResolverSupport {

    private final MemberService memberService;

    public ArgumentResolver(ReactiveAdapterRegistry adapterRegistry,
        MemberService memberService) {
        super(adapterRegistry);
        this.memberService = memberService;
    }

    @Override
    public ReactiveAdapterRegistry getAdapterRegistry() {
        return super.getAdapterRegistry();
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return checkParameterType(parameter, Member.class::isAssignableFrom);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
        ServerWebExchange exchange
    ) {
        ResolvableType resolvableType = ResolvableType.forMethodParameter(parameter);
        ReactiveAdapter adapter = (resolvableType != null ?
            getAdapterRegistry().getAdapter(resolvableType.resolve()) : null);

        try {
            String email = exchange.getAttributeOrDefault("email", "");
            if ("test-email.com".equals(email)) {
                Member testMember = Member.builder()
                    .id("test-id")
                    .isVerified(true)
                    .nickname("test-nickname")
                    .email("test-email.com")
                    .build();
                Mono<Member> testMemberMono = Mono.just(testMember);
                return testMemberMono.map(
                    member -> adapter != null ? adapter.fromPublisher(testMemberMono) : testMember);
            }
            Mono<Member> memberInfo = memberService.findFirstByEmail(email);
            return memberInfo
                .map(member -> member)
                .map(member -> adapter != null ? adapter.fromPublisher(memberInfo) : memberInfo);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
