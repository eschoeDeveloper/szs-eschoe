package com.szs.restapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
   title = "SZS API Docs",
   description = "삼쩜삼 채용공고 과제용 RESTFul API입니다.",
   version = "v1"
))
public class SwaggerConfig {

    @Bean("openAPIBySwagger")
    public OpenAPI openAPIBySwagger() {

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("JWT");

        SecurityScheme securityScheme = new SecurityScheme();
        Components components = new Components();

        securityScheme.name("JWT")
                      .type(SecurityScheme.Type.HTTP)
                      .scheme("Bearer")
                      .bearerFormat("JWT");

        components.addSecuritySchemes("JWT", securityScheme);

        OpenAPI openAPIBySwagger = new OpenAPI()
                                    .addSecurityItem(securityRequirement)
                                    .components(components);

        return openAPIBySwagger;

    }


}
