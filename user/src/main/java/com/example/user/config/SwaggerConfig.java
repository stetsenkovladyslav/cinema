package com.example.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
//@OpenAPIDefinition(
//        info = @Info(title = "User API", version = "v1"),
//        servers = {
//                @Server(url = "http://localhost:8081/", description = "user"),
//        }
//
//
//)
//@SecurityScheme(
//        name = "JWT",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer",
//        paramName = "Authorization",
//        bearerFormat = "JWT"
//)
////@Profile("dev")
//public class SwaggerConfig {
//    @Bean
//    public Docket swagger() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//}

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new io.swagger.v3.oas.models.security.SecurityScheme().type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER).name("Authorization")))
                .info(new Info().title("App API").version("snapshot"))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }
}
