package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    //옵션 설정
    // 부가적으로 API 문서에 대한 내용을 수정하거나 추가하고 싶을 때
    // Swgger URL: http://<ip>:<port>/<base>/swagger-ui.html
    //http://localhost:8080/swagger-ui.html

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("제목 작성")
            .version("버전 작성")
            .description("설명 작성")
            .license("라이센스 작성")
            .licenseUrl("라이센스 URL 작성")
            .build();
    }

}
