package page.aaws.myplatform.global.security.jwt;

public class JwtBearerTokenHttpServletRequestResolver implements JwtTokenHttpServletRequestResolver {
    @Override
    public String resolve(jakarta.servlet.http.HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return null;
        }
        return bearerToken.substring(7);
    }
}
