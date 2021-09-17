package com.parameta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import com.parameta.api.web.dto.UserSessionDTO;
import com.parameta.api.web.interceptor.JWTValidateInterceptor;
import com.parameta.api.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public Docket Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(appInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(appPaths())
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                .title("ParametaApi")
                .description("ParametaApi")
                .contact(new Contact("William Rubiano", "https://www.linkedin.com/in/wrubianom12/", "wrubianom1@gmail.com"))
                .version("1.0")
                .license("Apache License Version 2.0")
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "swagger-ui.html").setContextRelative(Boolean.TRUE);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean(name = "UserSessionDTO")
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSessionDTO userBean() {
        return new UserSessionDTO();
    }

    @Bean
    public UserInterceptor userInterceptor() {
        return new UserInterceptor(userBean());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger*/**", "springfox*/**", "/actuator*/**", "/");
        registry.addInterceptor(jwtValidateInterceptor())
                .addPathPatterns("/satellite/topsecret_split/**")
                .excludePathPatterns("/swagger*/**", "springfox*/**", "/actuator*/**", "/");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Authorization")
                .maxAge(3600);
    }


    private Predicate<String> appPaths() {
        return regex("/.*");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
        addDefaultHttpMessageConverters(converters);
    }

    @Bean
    public JWTValidateInterceptor jwtValidateInterceptor() {
        return new JWTValidateInterceptor(userBean());
    }

}