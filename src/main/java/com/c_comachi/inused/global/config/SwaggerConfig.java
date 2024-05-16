package com.c_comachi.inused.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "INUsed API 명세서",
                description = "INUsed API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        ArrayList<Server> servers = new ArrayList<>();
        servers.add(new Server().url("http://localhost:8080").description("Local Server"));

        //새로운 서버 url 추가하기
        servers.add(new Server().url("https://api.inused.store").description("Company Server"));

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement))
                .info(new Info().title("My Service").description("My Service Description")
                        .license(new License().url("http://MyDomainLicence.com").name("My info"))
                        .contact(new Contact().name("jk")
                                .email("bblbblan69@gmail.com")
                                .url("http://contactDomain.com"))
                        .version("1.0.0"))
                .servers(servers);
    }
}
