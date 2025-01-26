package com.joao.backend_frota.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("CRUD Frota API")
                .description("Documentação da API de Frota.")
                .version("1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
