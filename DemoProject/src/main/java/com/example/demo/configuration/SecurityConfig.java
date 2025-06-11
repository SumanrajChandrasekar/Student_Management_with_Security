//package com.example.demo.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
//                .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No session
//            .httpBasic(Customizer.withDefaults()); // Basic auth if needed (for testing)
//
//        return http.build();
//    }
//}

//    package com.example.demo.configuration;
//
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.http.HttpMethod;
//    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//    import org.springframework.security.web.SecurityFilterChain;
//    import org.springframework.security.config.Customizer;
//    import org.springframework.security.config.http.SessionCreationPolicy;
//
//    @Configuration
//    @EnableWebSecurity
//    public class SecurityConfig {
//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
//                .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
//                    //Restrict student creation to ADMIN role
//                    .requestMatchers(HttpMethod.POST, "/api/auth/students").hasRole("ADMIN")
//                    //Restrict student update and delete to ADMIN role
//                    .requestMatchers(HttpMethod.PUT, "/api/auth/student/**").hasRole("ADMIN")
//                    .requestMatchers(HttpMethod.DELETE, "/api/auth/students/**").hasRole("ADMIN")
//                    //Allow read access to all authenticated users
//                    .requestMatchers(HttpMethod.GET, "/api/auth/students/**", "/api/auth/getstudents", "/api/auth/student/**").authenticated()
//                    .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No session
//                .httpBasic(Customizer.withDefaults()); // Basic auth
//
//            return http.build();
//        }
//    }
    
    package com.example.demo.configuration;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.http.SessionCreationPolicy;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/login", "/api/auth/register", "/api/students/**", "/api/auth/getstudents", "/api/auth/student/**").permitAll() // ADDED PERMITALL FOR STUDENT ENDPOINTS
                    .anyRequest().permitAll() // IMPORTANT:  Permit all other requests too, for testing
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No session
                .httpBasic(Customizer.withDefaults()); // Basic auth

            return http.build();
        }
    }
    




