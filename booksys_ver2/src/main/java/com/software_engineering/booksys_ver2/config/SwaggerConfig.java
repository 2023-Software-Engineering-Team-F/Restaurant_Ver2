package com.software_engineering.booksys_ver2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  private static final String API_NAME = "booksys_ver2 API";
  private static final String API_DESCRIPTION = "booksys_ver2 API 명세서";

  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(API_NAME)
        .description(API_DESCRIPTION)
        .contact(new Contact(
            "[booksys_ver2]",
            "https://github.com/2023-Software-Engineering-Team-F",
            "awfjol2008@gmail.com"
        ))
        .build();
  }

  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.software_engineering.booksys_ver2"))
        .paths(PathSelectors.any())
        .build();
  }
}
