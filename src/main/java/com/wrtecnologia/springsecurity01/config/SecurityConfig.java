package com.wrtecnologia.springsecurity01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Filtros
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/public").permitAll();
                            authorizeConfig.requestMatchers("/swagger-ui/**", "/v3/api-docs").permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        })
                /** TODO .formLogin(Customizer.withDefaults()) //Login screen default do Oauth2 */
                .oauth2Login(
                        Customizer.withDefaults()).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                            JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                            jwt.jwtAuthenticationConverter(jwtAuthenticationConverter);
                        }
                )).build();
    }
}
