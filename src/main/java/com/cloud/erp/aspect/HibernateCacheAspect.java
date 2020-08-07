package com.cloud.erp.aspect;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cloud.erp.service.BaseService;

/**
 * @author YANGKAIQI1
 */
@Aspect
@Component
@Lazy(false)
public class HibernateCacheAspect {
    private static final String[] FIND_METHODS = new String[] {"find", "get"};

    @Pointcut("@within(com.cloud.erp.annotation.HibernateCacheAnnotation) && this(com.cloud.erp.service.BaseService)")
    public void addAdvice() {}

    @AfterReturning("addAdvice()")
    public void afterReturning(JoinPoint joinPoint) throws Exception {
        Objects.requireNonNull(joinPoint);
        BaseService baseService = (BaseService)joinPoint.getThis();
        if (Optional.ofNullable(joinPoint.getSignature()).map(Signature::getName)
            .filter(v -> StringUtils.startsWithAny(v, FIND_METHODS)).isPresent()) {
            baseService.clear();
        } else {
            baseService.flush();
            baseService.clear();
        }

    }

}
