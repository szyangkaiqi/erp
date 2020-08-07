package com.cloud.erp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.erp.pojo.vo.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "", description = "登录")
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @ApiOperation(value = "接收确认")
    @PostMapping(value = "/login")
    public Result<String> login(String userName, String password) throws Exception {
        int i = 1 / 0;
        return Result.<String>makeSuccess();
    }

}

