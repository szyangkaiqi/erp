package com.cloud.erp.exception;

import lombok.Data;

/**
 * @author YANGKAIQI1
 */
@Data
public class CaptchaException extends RuntimeException {

    private String msg;

    public CaptchaException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
