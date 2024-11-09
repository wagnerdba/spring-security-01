package com.wrtecnologia.springsecurity01.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OAuth2 com JWT - Spring Boot 3 - Google API Authentication")
                        .version("1.0")
                        .description("OAuth 2 - Integração à Google API, autenticação com usuário e senha do google")
                );
    }
}
