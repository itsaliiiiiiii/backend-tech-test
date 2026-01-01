package com.example.recipes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI recipeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recipe Management API")
                        .description("API for Recipe Management Application")
                        .version("1.0"));
    }
}
