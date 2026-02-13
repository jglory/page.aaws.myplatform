package page.aaws.myplatform.global.security.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import page.aaws.myplatform.global.security.jwt.model.JwtAuthenticationToken;
import page.aaws.myplatform.global.security.jwt.JwtTokenHttpServletRequestResolver;

import java.io.IOException;

@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenHttpServletRequestResolver jwtTokenHttpServletRequestResolver;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = this.jwtTokenHttpServletRequestResolver.resolve(request);
        if (token != null) {
            try {
                JwtAuthenticationToken jwtAuthenticationToken = JwtAuthenticationToken.unauthenticated(token);
                Authentication authentication = this.authenticationManager.authenticate(jwtAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
