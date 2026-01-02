package splitable.backend.billscanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // For Local 
        // registry.addMapping("/api/**")
        //         .allowedOrigins("http://localhost:5173")
        //         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        //         .allowedHeaders("*")
        //         .allowCredentials(true);
        
        // When Hosted in GIT
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://hey-gana.github.io"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
