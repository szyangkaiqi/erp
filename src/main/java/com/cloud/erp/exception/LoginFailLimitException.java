package com.cloud.erp.exception;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

import lombok.Data;

/**
 * @author YANGKAIQI1
 */
@Data
public class LoginFailLimitException extends InternalAuthenticationServiceException {

    private String msg;

    public LoginFailLimitException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LoginFailLimitException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }
}
