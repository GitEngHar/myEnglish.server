package myenglish.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${front.path}")
    private String redirectUrl; //リダイレクト先のURL local: http://localhost:3000
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        redirectUrl
                        )
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);  // Cookieの送信を許可
    }

}

