package com.deltacode.springbootblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.deltacode.springbootblog"))
                .build()
                .apiInfo(apiInfo());

    }
    private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "Blog API",
                "Spring Boot REST API for Blog Application",
                "1.0",
                "Free to use",
                new Contact("David Charo", "https://agitated-tesla-ccf7b9.netlify.app/", "davidcharomakuba@fmail.com"),
                "Api License",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new java.util.ArrayList<>()
        );
        return apiInfo;
    }


}
