package page.aaws.myplatform.global.security.config.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.stereotype.Component;

@Component
public class HttpBasicCustomizer implements Customizer<HttpBasicConfigurer<HttpSecurity>> {
    @Override
    public void customize(HttpBasicConfigurer<HttpSecurity> httpSecurityHttpBasicConfigurer) {
        httpSecurityHttpBasicConfigurer.disable();
    }
}
