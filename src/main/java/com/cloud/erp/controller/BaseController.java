package com.cloud.erp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.erp.pojo.vo.Result;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BaseController {

    /**
     * 统一异常处理类
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public Result exp(HttpServletRequest request, Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.makeFail(ex.getMessage(), ex);
    }

}
