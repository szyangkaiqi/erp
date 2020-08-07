package com.cloud.erp.exception;

import lombok.Data;

/**
 * @author YANGKAIQI1
 */
@Data
public class XbootException extends RuntimeException {

    private String msg;

    public XbootException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
