package com.cloud.erp.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.erp.constant.MessageConstant;
import com.cloud.erp.pojo.dto.UserwDTO;
import com.cloud.erp.pojo.vo.Result;
import com.cloud.erp.pojo.vo.UserInfo;
import com.cloud.erp.service.UserwService;
import com.cloud.erp.utils.ErpContext;
import com.cloud.erp.utils.JWTTokenUtil;
import com.cloud.erp.utils.MessageUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", description = "登录")
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private UserwService userwService;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public Result<String> login(@ApiParam(value = "登录", required = true) @RequestBody UserInfo userInfo)
        throws Exception {
        Objects.requireNonNull(userInfo, MessageUtils.get(MessageConstant.USER_NAME_IS_NULL.name()));
        Objects.requireNonNull(userInfo.getUserName(), MessageUtils.get(MessageConstant.USER_NAME_IS_NULL.name()));
        Optional<UserwDTO> user = userwService.findByName(userInfo.getUserName());

        if (user.isEmpty()) {
            return Result.makeFail(MessageUtils.get(MessageConstant.USER_NAME_OR_PASSWORD_ERROR.name()));
        }

        Result<String> result = Result.makeSuccess();
        result.setData(JWTTokenUtil.generateToken(user.get().getUserId(), user.get().getName()));
        return result;

    }

    @ApiOperation(value = "获取当前登录人信息")
    @GetMapping(value = "/getCurrentUserInfo")
    public Result<UserInfo> getCurrentUserInfo() throws Exception {

        Optional<UserInfo> userInfo = ErpContext.getUserInfo();

        if (userInfo.isEmpty()) {
            return Result.makeFail(MessageUtils.get(MessageConstant.USER_GET_CURRENT_USER_INFO.name()));
        }

        return Result.makeSuccess(userInfo.get());
    }

}
