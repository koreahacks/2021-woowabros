package koreahacks.woowabros.uniconn;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

import koreahacks.woowabros.uniconn.common.ArgumentResolver;
import koreahacks.woowabros.uniconn.member.application.MemberService;

@Configuration
@EnableWebFlux
public class CorsConfiguration implements WebFluxConfigurer {

    private final ArgumentResolver argumentResolver;

    public CorsConfiguration(MemberService memberService) {
        this.argumentResolver = new ArgumentResolver(ReactiveAdapterRegistry.getSharedInstance(),
            memberService);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("*")
            .maxAge(3600);
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(argumentResolver);
    }
}
