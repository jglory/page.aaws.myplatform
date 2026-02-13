package page.aaws.myplatform.global.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.security.Keys;
import page.aaws.myplatform.global.security.jwt.*;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import page.aaws.myplatform.global.security.jwt.filter.JwtAuthenticationFilterFailureHandler;
import page.aaws.myplatform.global.security.jwt.filter.JwtAuthenticationFilterSuccessHandler;
import page.aaws.myplatform.global.security.jwt.provider.JwtAuthenticationProvider;

import java.nio.charset.StandardCharsets;

@Configuration
@ConfigurationPropertiesScan(basePackages = "page.aaws.myplatform.global.security.jwt")
@EnableConfigurationProperties({JwtConfiguration.class})
@AllArgsConstructor
public class JwtConfig {
    private final JwtConfiguration jwtConfiguration;

    @Bean
    @Qualifier("jwtAccessTokenManager")
    public JwtTokenManager accessTokenManager() {
        return new JwtTokenManager(Keys.hmacShaKeyFor(this.jwtConfiguration.accessToken().secretKey().getBytes(StandardCharsets.UTF_8)), this.jwtConfiguration.accessToken().validitySeconds(), this.jwtConfiguration.accessToken().issuer());
    }

    @Bean
    @Qualifier("jwtRefreshTokenManager")
    public JwtTokenManager refreshTokenManager() {
        return new JwtTokenManager(Keys.hmacShaKeyFor(this.jwtConfiguration.refreshToken().secretKey().getBytes(StandardCharsets.UTF_8)), this.jwtConfiguration.refreshToken().validitySeconds(), this.jwtConfiguration.refreshToken().issuer());
    }

    @Bean
    JwtAuthenticationProvider jwtAccessTokenAuthenticationProvider(JwtTokenManager accessTokenManager) {
        return new JwtAuthenticationProvider(accessTokenManager);
    }

    @Bean
    JwtAuthenticationProvider jwtRefreshTokenAuthenticationProvider(JwtTokenManager refreshTokenManager) {
        return new JwtAuthenticationProvider(refreshTokenManager);
    }

    @Bean
    JwtAuthenticationFilterFailureHandler jwtAuthenticationFilterFailureHandler(ObjectMapper objectMapper) {
        return new JwtAuthenticationFilterFailureHandler(objectMapper);
    }

    @Bean
    JwtAuthenticationFilterSuccessHandler jwtAuthenticationFilterSuccessHandler(JwtTokenManager accessTokenManager, JwtTokenManager refreshTokenManager, ObjectMapper objectMapper) {
        return new JwtAuthenticationFilterSuccessHandler(accessTokenManager, refreshTokenManager, objectMapper);
    }

    @Bean
    JwtTokenHttpServletRequestResolver jwtTokenHttpServletRequestResolver() {
        return new JwtBearerTokenHttpServletRequestResolver();
    }
}
