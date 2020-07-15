package com.nextloop.nearlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.nextloop.nearlog"))
                    .paths(PathSelectors.ant("/api/v1/**"))
                    .build();
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("Spring API Documentation")
                .description("nearlog 서버 API 문서")
                .license("bgpark")
                .licenseUrl("https://bgpark.tistory.com/")
                .contact("bgpark82@gmail.com")
                .build();
    }
}
