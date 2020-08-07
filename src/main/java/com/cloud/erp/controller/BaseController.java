package com.cloud.erp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.erp.pojo.vo.Result;

@RestController
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 统一异常处理类
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public Result exp(HttpServletRequest request, Exception ex) {
        return new Result().makeFail(ex.getMessage());
    }

}
