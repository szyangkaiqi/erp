package com.cloud.erp.erp.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).globalRequestParameters(createGlobalParams()).select()
            .apis(RequestHandlerSelectors.basePackage("com.cloud.erp.erp")).paths(PathSelectors.any()).build()
            .apiInfo(new ApiInfoBuilder().title("云ERP").description("©2020 Copyright. ")
                .contact(new Contact("admin", "http://www.erp.cloud.com.cn", "admin@cloud.erp.hk")).license("云erp")
                .build())
            .useDefaultResponseMessages(false);
    }

    private List<RequestParameter> createGlobalParams() {
        List<RequestParameter> requestParameters = Lists.newArrayList();
        requestParameters.add(new RequestParameterBuilder().in(ParameterType.HEADER).name("token").required(false)
            .description("header中token字段").build());

        return requestParameters;
    }
}
