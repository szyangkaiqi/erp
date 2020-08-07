package com.cloud.erp.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.erp.constant.MessageConstant;
import com.cloud.erp.pojo.entity.TUserwDO;
import com.cloud.erp.pojo.vo.Result;
import com.cloud.erp.service.TUserwService;
import com.cloud.erp.utils.MessageUtils;

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
        Objects.requireNonNull(userName, MessageUtils.get(MessageConstant.USER_NAME_IS_NULL.name()));
        Optional<TUserwDO> user = userwService.findByName(userName);

        if (user.isEmpty()) {
            return Result.makeFail(MessageUtils.get(MessageConstant.USER_NAME_OR_PASSWORD_ERROR.name()));
        }

        return Result.makeSuccess();

    }

}

