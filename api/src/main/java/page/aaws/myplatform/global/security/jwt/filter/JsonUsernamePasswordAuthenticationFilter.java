package page.aaws.myplatform.global.security.jwt.filter;


import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import page.aaws.myplatform.global.security.jwt.model.JsonUsernamePasswordAuthenticationRequest;

public class JsonUsernamePasswordAuthenticationFilter<T extends JsonUsernamePasswordAuthenticationRequest> extends UsernamePasswordAuthenticationFilter {
    private T authenticationRequest;
    private final Class<T> requestClass;
    private final ObjectMapper objectMapper;

    public JsonUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, Class<T> requestClass, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.requestClass = requestClass;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        this.authenticationRequest = this.parseRequestToLoginRequest(request);
        return super.attemptAuthentication(request, response);
    }

    @Nullable
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return this.authenticationRequest.getPassword();
    }

    @Nullable
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return this.authenticationRequest.getUsername();
    }

    protected T parseRequestToLoginRequest(HttpServletRequest request) throws AuthenticationServiceException {
        try {
            return this.objectMapper.readValue(request.getInputStream(), this.requestClass);
        } catch (IOException exception) {
            throw new AuthenticationServiceException(exception.getMessage());
        }
    }
}
