package com.cloud.erp.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30).globalRequestParameters(createGlobalParams()).select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build()
            .apiInfo(apiInfo()).select().build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("云erp接口文档").description("云erp接口文档")
            .contact(new Contact("admin。", "http://www.erp.cloud.com.cn", "admin@cloud.erp.hk")).version("1.0").build();
    }

    private List<RequestParameter> createGlobalParams() {
        List<RequestParameter> requestParameters = Lists.newArrayList();
        requestParameters.add(new RequestParameterBuilder().in(ParameterType.HEADER).name("token").required(false)
            .description("header中token字段").build());

        return requestParameters;
    }
}
