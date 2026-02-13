package page.aaws.myplatform.global.security.config.customizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import page.aaws.myplatform.global.exception.AppError;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExceptionHandlingCustomizer implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {
    private final ObjectMapper objectMapper;

    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandlingConfigurer) {
        exceptionHandlingConfigurer
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            AppResponse<Void> appResponse = AppResponse.error(AppStatus.UNAUTHORIZED, AppError.of(AppStatus.UNAUTHORIZED, "인증에 실패하였습니다."));
            response.getWriter().write(objectMapper.writeValueAsString(appResponse));
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            AppResponse<Void> appResponse = AppResponse.error(AppStatus.FORBIDDEN, AppError.of(AppStatus.FORBIDDEN, "접근 권한이 없습니다."));
            response.getWriter().write(objectMapper.writeValueAsString(appResponse));
        };
    }
}
