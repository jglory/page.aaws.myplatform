package page.aaws.myplatform.global.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import page.aaws.myplatform.global.exception.AppError;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@AllArgsConstructor
public class JwtAuthenticationFilterFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        AppResponse<Void> appResponse = AppResponse.error(AppStatus.UNAUTHORIZED, AppError.of(AppStatus.UNAUTHORIZED, this.getErrorMessage(exception)));
        response.getWriter().write(this.objectMapper.writeValueAsString(appResponse));
    }

    private String getErrorMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            return "아이디 또는 비밀번호가 일치하지 않습니다.";
        } else if (exception instanceof LockedException) {
            return "계정이 잠겨 있습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof DisabledException) {
            return "계정이 비활성화되었습니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            return "비밀번호 유효기간이 만료되었습니다.";
        } else if (exception instanceof AccountExpiredException) {
            return "만료된 계정입니다.";
        } else if (exception instanceof InsufficientAuthenticationException) {
            return "인증에 필요한 정보가 불충분합니다.";
        }
        return "인증에 실패하였습니다. 다시 시도해 주세요.";
    }
}