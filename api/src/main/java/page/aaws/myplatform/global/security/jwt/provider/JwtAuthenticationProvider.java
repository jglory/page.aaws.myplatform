package page.aaws.myplatform.global.security.jwt.provider;

import io.jsonwebtoken.JwtException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import page.aaws.myplatform.global.security.jwt.JwtTokenManager;
import page.aaws.myplatform.global.security.jwt.model.JwtAuthenticationToken;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTokenManager jwtTokenManager;

    public JwtAuthenticationProvider(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        if (token == null || !this.jwtTokenManager.validateToken(token)) {
            throw new BadCredentialsException("잘못 되었거나 만료된 토큰입니다.");
        }

        try {
            return this.jwtTokenManager.getJwtAuthenticationTokenFromToken(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
