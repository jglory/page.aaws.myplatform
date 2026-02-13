package page.aaws.myplatform.global.security.config.customizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeHttpRequestsCustomizer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
    @Value("${springdoc.api-docs.path:/v1/api-docs}")
    String apiDocsPath;

    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {
        authorizationManagerRequestMatcherRegistry
                .requestMatchers(
                    "/api/v1/health",
                    "/api/v1/auth/login",
                    "/api/v1/auth/reissue",
                    "/api/v1/auth/check-email/**",
                    "/api/v1/customer/signup",
                    "/api/v1/partner/signup",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/swagger-resources/**",
                    this.apiDocsPath + "/**",
                    "/error",
                    "/favicon.ico"
                ).permitAll()
                .requestMatchers("/api/v1/reissue").authenticated()
                .requestMatchers("/api/v1/echo").hasRole("CUSTOMER")
                .requestMatchers("/api/v1/partner").hasRole("PARTNER")
                .requestMatchers("/api/v1/admin").hasRole("ADMIN")
                .anyRequest().authenticated();
    }
}
