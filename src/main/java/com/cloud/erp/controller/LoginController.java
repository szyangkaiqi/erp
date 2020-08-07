package com.cloud.erp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.erp.pojo.vo.Result;
import com.cloud.erp.service.TUserwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "", description = "登录")
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private TUserwService userwService;

    @ApiOperation(value = "接收确认")
    @PostMapping(value = "/login")
    public Result<String> login(String userName, String password) throws Exception {
        Objects.requireNonNull(userName);
        userwService.findByName(userName);
        int i = 1 / 0;
        return Result.<String>makeSuccess();
    }

}

