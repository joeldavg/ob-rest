package com.joeldavg.obrestdatajpa.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Book API REST")
                        .description("Library Api Rest docs")
                        .version("v0.0.1")
                        .license(new License().name("Spring Boot Rest")
                                .url("http://google.com.co"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

    /*@Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Spring Boot Book API REST",
                "Library Api Rest docs",
                "1.0",
                "http://www.google.com",
                new Contact("Joel Guzman", "http://www.google.com", "joel@mail.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }*/

}
