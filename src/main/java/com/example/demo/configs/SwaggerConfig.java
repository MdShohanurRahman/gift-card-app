/**
 * Created By shoh@n
 * Date: 9/19/2022
 */

package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "CQRS & ES Sample GiftCard app on Spring Boot and Axon",
                "App to demonstrate CQRS & ES based on Spring Boot and Axon",
                "0.0.1-SNAPSHOT",
                "Terms of Service",
                new Contact("Shohanur Rahman",
                        "https://shohanur.com",
                        "shohan.drmc@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }

}
