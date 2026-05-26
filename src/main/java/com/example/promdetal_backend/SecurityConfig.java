package com.example.promdetal_backend;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ✅ CORS ВАЖНО: первым
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // ❌ CSRF не нужен для JWT
                .csrf(AbstractHttpConfigurer::disable)

                // ❌ Сессии не используем
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // ✅ PRE-FLIGHT (ОБЯЗАТЕЛЬНО)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/groups/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/equipment/**").permitAll()
                        // ---------- PUBLIC ----------

                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Files/**").permitAll()
                        .requestMatchers("/api/groups").permitAll()
                        .requestMatchers("/api/contact").permitAll()
                        .requestMatchers("/api/articles").permitAll()
                        .requestMatchers("/api/equipment").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api/news/**",
                                "/api/cases/**",
                                "/api/partners/**",
                                "/api/equipment/**"
                        ).permitAll()

                        // ---------- PROTECTED ----------
                        .requestMatchers(HttpMethod.POST, "/api/Files").authenticated()
                        .requestMatchers("/api/groups/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/equipment/{equipmentId}/cases").authenticated()

                        .requestMatchers("/api/articles/**").authenticated()

                        // ---------- EVERYTHING ELSE ----------
                        .anyRequest().authenticated()
                )

                // ✅ JWT фильтр ПОСЛЕ CORS
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ ГЛОБАЛЬНЫЙ CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:8081",
                "http://172.29.0.1:8081/",
                "http://157.22.174.170"
        ));

        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
