package com.example.ewallet.Configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "E-Wallet Service API",
                version = "v1.0",
                description = "Secure backend system for wallet management, transactions, and authentication using JWT.",
                contact = @Contact(
                        name = "Harsh Chopda",
                        email = "harshchopda05@gmail.com",
                        url = "https://github.com/HarshChopda05"
                ),
                license = @License(
                        name = "Open Source License",
                        url = "API License URL"
                )
        ),
        security = @SecurityRequirement(name = "bearerAuth"), //APIs show Lock Icon in Swagger UI
        externalDocs = @ExternalDocumentation(
                description = "Project Documentation",
                url = "https://github.com/HarshChopda05/E-Wallet-Service_API/blob/master/Documentation.docx"
        )
)

@SecurityScheme( //for Authorization button
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

@Configuration
public class SwaggerConfig {

    //config (manual control + customization)
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes("bearerAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("E-Wallet-apis")
                .pathsToMatch("/api/**")
                .build();
    }
}
