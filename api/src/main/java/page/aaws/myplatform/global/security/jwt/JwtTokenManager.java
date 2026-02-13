package page.aaws.myplatform.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import page.aaws.myplatform.global.security.jwt.model.JwtAuthenticationToken;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class JwtTokenManager {
    private static final String CLAIM_ROLES = "roles";

    private final SecretKey secretKey;
    private final long validitySeconds;
    private final String issuer;

    public JwtAuthenticationToken getJwtAuthenticationTokenFromToken(String token) throws JwtException, IllegalArgumentException {
        Claims claims = parseClaims(token);

        List<?> roles = claims.get(CLAIM_ROLES, List.class);

        return JwtAuthenticationToken.authenticated(
                claims.getSubject(),
                token,
                roles.stream()
                        .map(Object::toString)
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
    }

    public String createToken(AbstractAuthenticationToken authenticationToken) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(this.validitySeconds);

        return Jwts.builder()
                .subject(authenticationToken.getName())
                .issuer(this.issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .claim(CLAIM_ROLES,
                        authenticationToken
                                .getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                )
                .signWith(this.secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        return this.parseClaims(token).getExpiration().after(new Date());
    }

    private Claims parseClaims(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build().parseSignedClaims(token).getPayload();
    }
}
