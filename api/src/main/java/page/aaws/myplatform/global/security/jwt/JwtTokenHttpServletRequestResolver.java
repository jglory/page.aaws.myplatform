package page.aaws.myplatform.global.security.jwt;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtTokenHttpServletRequestResolver {
    String resolve(HttpServletRequest request);
}
