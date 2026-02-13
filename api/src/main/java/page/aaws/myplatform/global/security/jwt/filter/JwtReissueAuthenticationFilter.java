package page.aaws.myplatform.global.security.jwt.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import page.aaws.myplatform.global.security.jwt.model.JwtAuthenticationToken;
import page.aaws.myplatform.global.security.jwt.JwtTokenHttpServletRequestResolver;

public class JwtReissueAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final JwtTokenHttpServletRequestResolver jwtTokenHttpServletRequestResolver;

    public JwtReissueAuthenticationFilter(
            String filterProcessesUrl,
            AuthenticationManager authenticationManager,
            JwtTokenHttpServletRequestResolver jwtTokenHttpServletRequestResolver,
            AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler
            ) {
        super(filterProcessesUrl, authenticationManager);
        this.setAuthenticationSuccessHandler(successHandler);
        this.setAuthenticationFailureHandler(failureHandler);
        this.jwtTokenHttpServletRequestResolver = jwtTokenHttpServletRequestResolver;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!HttpMethod.POST.matches(request.getMethod())) {
            throw new AuthenticationServiceException("잘못된 요청입니다: " + request.getMethod());
        }

        String token = this.jwtTokenHttpServletRequestResolver.resolve(request);
        if (token == null) {
            throw new InsufficientAuthenticationException("인증에 필요한 정보가 불충분합니다.");
        }

        JwtAuthenticationToken jwtAuthenticationToken = JwtAuthenticationToken.unauthenticated(token);
        return this.getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }
}
