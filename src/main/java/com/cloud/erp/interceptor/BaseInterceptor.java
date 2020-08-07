package com.cloud.erp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author YANGKAIQI1
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {

    protected static Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    private BaseInterceptor[] nextInterceptor;

    public void setNextInterceptor(BaseInterceptor... nextInterceptor) {
        this.nextInterceptor = nextInterceptor;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (nextInterceptor == null) {
            return true;
        }
        for (BaseInterceptor baseInterceptor : nextInterceptor) {
            if (!baseInterceptor.preHandle(request, response, handler)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        if (nextInterceptor != null) {
            for (int i = 0; i < nextInterceptor.length; i++) {
                nextInterceptor[i].postHandle(request, response, handler, modelAndView);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        if (nextInterceptor != null) {
            for (BaseInterceptor baseInterceptor : nextInterceptor) {
                baseInterceptor.afterCompletion(request, response, handler, ex);
            }
        }
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (nextInterceptor != null) {
            for (BaseInterceptor baseInterceptor : nextInterceptor) {
                baseInterceptor.afterConcurrentHandlingStarted(request, response, handler);
            }
        }
    }
}
