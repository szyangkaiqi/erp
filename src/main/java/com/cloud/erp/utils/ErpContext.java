package com.cloud.erp.utils;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.cloud.erp.config.ThreadLocals;
import com.cloud.erp.pojo.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author YANGKAIQI1
 */
@Slf4j
public class ErpContext {

    private static final String TOKEN = "token";

    public static String getToken() {
        HttpServletRequest request = ThreadLocals.get(HttpServletRequest.class);

        if (null == request) {
            return StringUtils.EMPTY;
        }

        String token = request.getHeader(TOKEN);

        if (StringUtils.isBlank(token)) {
            return StringUtils.EMPTY;
        }

        String[] tokens = token.split("#");

        if (tokens.length != 2) {
            return StringUtils.EMPTY;
        }

        if (!Objects.equals(JWTTokenUtil.getMd5Token(tokens[0]), tokens[1])) {
            return StringUtils.EMPTY;
        }

        return token;

    }

    /**
     * @Description: 获取当前登录的用户
     * @Param: []
     * @return: java.util.Optional<com.cloud.erp.pojo.vo.UserInfo>
     * @Author: YANGKAIQI1
     * @Date: 2020-08-10
     */
    public static Optional<UserInfo> getUserInfo() throws Exception {
        if (StringUtils.isBlank(getToken())) {
            return Optional.empty();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(JWTTokenUtil.getUserIdByJWTToken(getToken().split("#")[0]));
        userInfo.setUserName(JWTTokenUtil.getUserNameByJWTToken(getToken().split("#")[0]));

        return Optional.of(userInfo);
    }
}
