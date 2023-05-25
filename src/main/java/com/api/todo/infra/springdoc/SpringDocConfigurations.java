package com.api.todo.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()
                            .addSecuritySchemes("bearer-key", new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")))
                            .info(new Info()
                            .title("Api Rest Full TO-DO")
                            .description("API REST FULL TO-DO, DESENVOLVIDA POR NAILTON DEV BACKEND")
                            .contact(new Contact()
                            .name(" -> Nailton Backend Java")
                            .email("jnmp25jnmp@gmail.com"))
                            .license(new License()
                            .name("API REST FULL DEV NAILTON ")
                            .url("http://localhost:8080/v3/api-docs")));
    }


}
