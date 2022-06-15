package com.microservice.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.microservice"))
                .paths(PathSelectors.any())
                .build().apiInfo(swaggerDescription());
    }

    /**
     * Describing Swagger UI: swagger doc generation
     * @return swagger UI description
     */
    private ApiInfo swaggerDescription(){
        return new ApiInfoBuilder().title("User Microservice Documentation")
                .description("\" Swagger API Documentation for user microservice\"")
                .version("1.0").contact(new Contact("Xavier Oduor", "https://github.com/Xavier-Frank", "oduorfrancis134@gmail.com"))
                .build();

    }

    /**
     * Add resources to the UI
     * @param registry adds a set of resources
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
