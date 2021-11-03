package com.example.admin.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Cinema API", version = "v1"),
        servers = {
                @Server(url = "http://localhost:8080/", description = "cinema"),
        }


)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        paramName = "Authorization",
        bearerFormat = "JWT"
)
@Profile("dev")
public class SwaggerConfig {
            @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}


//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
////    @Bean
////    public Docket swagger() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build();
////    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearer-jwt",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//                                .in(SecurityScheme.In.HEADER).name("Authorization")))
//                .info(new Info().title("App API").version("snapshot"))
//                .addSecurityItem(
//                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
//    }
//}