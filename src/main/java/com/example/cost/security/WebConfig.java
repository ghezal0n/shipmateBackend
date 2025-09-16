package com.example.cost.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permet toutes les routes
                .allowedOrigins("http://localhost:3000") // autorise le frontend
                .allowedMethods("*") // GET, POST, PUT, DELETE...
                .allowedHeaders("*") // autorise tous les headers
                .allowCredentials(true); // permet l’envoi du token
    }
}
