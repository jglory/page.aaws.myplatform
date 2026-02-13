package page.aaws.myplatform.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtConfiguration (
        JwtTokenProperties accessToken,
        JwtTokenProperties refreshToken,
        @DefaultValue List<String> webAgents
) {
    public record JwtTokenProperties (
        String secretKey,
        String issuer,
        Long validitySeconds
    ) {}
}
