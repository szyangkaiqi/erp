package com.cloud.erp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.collect.Maps;

/**
 * @author YANGKAIQI1
 */
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
                ThreadLocals.init(Maps.newHashMap());
                ThreadLocals.put(HttpServletRequest.class, request);
                ThreadLocals.put(HttpServletResponse.class, response);
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                @Nullable Exception ex) throws Exception {
                ThreadLocals.destroy();
            }
        });
    }
}
