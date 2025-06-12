    // package com.example.demo.configuration;

    // import org.springframework.context.annotation.Bean;
    // import org.springframework.context.annotation.Configuration;
    // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    // import org.springframework.security.web.SecurityFilterChain;
    // import org.springframework.security.config.Customizer;
    // import org.springframework.security.config.http.SessionCreationPolicy;

    // @Configuration
    // @EnableWebSecurity
    // public class SecurityConfig {

    //     @Bean
    //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //         http
    //             .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
    //             .authorizeHttpRequests(auth -> auth
    //                 .requestMatchers("/api/auth/login", "/api/auth/register", "/api/students/**", "/api/auth/getstudents", "/api/auth/student/**").permitAll() // ADDED PERMITALL FOR STUDENT ENDPOINTS
    //                 .anyRequest().permitAll() // IMPORTANT:  Permit all other requests too, for testing
    //             )
    //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No session
    //             .httpBasic(Customizer.withDefaults()); // Basic auth

    //         return http.build();
    //     }
    // }
    

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("https://demoprojectsuman.netlify.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}


