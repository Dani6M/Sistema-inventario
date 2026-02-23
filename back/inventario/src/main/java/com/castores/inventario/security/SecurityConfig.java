package com.castores.inventario.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/movimientos/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/productos/baja/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/productos/reactivar/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/productos/entrada").hasRole("ADMINISTRADOR")

                .requestMatchers("/productos/salida").hasRole("ALMACENISTA")

                .requestMatchers("/productos/**")
                .hasAnyRole("ADMINISTRADOR","ALMACENISTA")

                .anyRequest().authenticated()
            )
            .httpBasic();   

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

