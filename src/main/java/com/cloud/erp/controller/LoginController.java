package com.cloud.erp.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.erp.constant.MessageConstant;
import com.cloud.erp.pojo.dto.UserwDTO;
import com.cloud.erp.pojo.vo.Result;
import com.cloud.erp.service.UserwService;
import com.cloud.erp.utils.JWTTokenUtil;
import com.cloud.erp.utils.MessageUtils;

import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "", description = "登录")
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private UserwService userwService;

    @ApiOperation(value = "接收确认")
    @PostMapping(value = "/login")
    public Result<String> login(String userName, String password) throws Exception {
        Objects.requireNonNull(userName, MessageUtils.get(MessageConstant.USER_NAME_IS_NULL.name()));
        Optional<UserwDTO> user = userwService.findByName(userName);

        if (user.isEmpty()) {
            return Result.makeFail(MessageUtils.get(MessageConstant.USER_NAME_OR_PASSWORD_ERROR.name()));
        }

        String token = JWTTokenUtil.generateToken(user.get().getUserId(), user.get().getName());

        token = token + "#" + SecureUtil.md5(token);
        return Result.makeSuccess(token);

    }

}
