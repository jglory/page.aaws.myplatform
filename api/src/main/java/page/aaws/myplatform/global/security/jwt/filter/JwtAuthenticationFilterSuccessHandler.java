package page.aaws.myplatform.global.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import page.aaws.myplatform.api.v1.auth.dto.TokenResponse;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import page.aaws.myplatform.global.security.jwt.JwtTokenManager;

import java.io.IOException;

@AllArgsConstructor
public class JwtAuthenticationFilterSuccessHandler implements AuthenticationSuccessHandler {
    protected final JwtTokenManager accessTokenManager;
    protected final JwtTokenManager refreshTokenManager;
    protected final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessToken = this.accessTokenManager.createToken((AbstractAuthenticationToken) authentication);
        String refreshToken = this.refreshTokenManager.createToken((AbstractAuthenticationToken) authentication);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        AppResponse<TokenResponse<Void>> appResponse = AppResponse.success(AppStatus.OK, TokenResponse.of(accessToken, refreshToken, "read write", "Bearer"));
        response.getWriter().write(this.objectMapper.writeValueAsString(appResponse));
    }
}
