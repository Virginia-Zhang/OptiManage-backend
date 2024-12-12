package com.virginia.config;

import com.virginia.filter.JWTAuthenticationFilter;
import com.virginia.service.impl.MyUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Resource
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    // Custom Authentication Manager

    @Bean
    public AuthenticationManager authenticationManager() {
        // Create a new DaoAuthenticationProvider object, specifying userDetailsService and passwordEncoder
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        // Create a new ProviderManager object, passing in the provider.
        return new ProviderManager(provider);
    }

    // Specify PasswordEncoder

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Do not check /api/login interface
        http.authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/login").permitAll()
                .anyRequest().authenticated())
                .formLogin(form->form.disable())
                // Put the jwt authentication filter before the last filter, authorization filter.
                .addFilterBefore(jwtAuthenticationFilter, AuthorizationFilter.class)
                .sessionManagement(session->session.disable())
                .csrf(csrf->csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .logout(logout -> logout.disable());
        return http.build();
    }

    // Global cors config source
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
//        config.addExposedHeader("authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

