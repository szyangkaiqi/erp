package com.cloud.erp.exception;

import lombok.Data;

/**
 * @author YANGKAIQI1
 */
@Data
public class LimitException extends RuntimeException {

    private String msg;

    public LimitException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
