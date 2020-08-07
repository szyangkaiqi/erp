package com.cloud.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cloud.erp.config.WebMvcConfig;

import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author YANGKAIQI1
 */
@SpringBootApplication
@EnableOpenApi
@EnableTransactionManagement
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }

    @Bean
    WebMvcConfigurer configWebMvc() {
        return new WebMvcConfig();
    }

}
