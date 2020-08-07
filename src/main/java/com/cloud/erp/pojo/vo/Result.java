package com.cloud.erp.pojo.vo;

import java.io.Serializable;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.cloud.erp.constant.BaseConstant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "返回结果代码", example = "Y：成功；N：失败；W: 警告  C:确认")
    private String errorFlag;

    @ApiModelProperty(value = "返回消息", example = "errorFlag =N errorMag一定有值")
    private String errorMag;

    /**
     * 返回错误异常堆栈(原始异常)
     */
    @ApiModelProperty(value = "返回错误异常堆栈", example = "返回错误异常堆栈")
    private String detailErrorMsgStack;

    @ApiModelProperty(value = "返回值")
    private T data;

    public static <T> Result<T> makeFail(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.N.toString()).errorMag(msg).build();
    }

    public static <T> Result<T> makeFail(String msg, Exception e) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.N.toString()).errorMag(msg)
            .detailErrorMsgStack(ExceptionUtils.getStackTrace(e)).build();
    }

    public static <T> Result<T> makeFail(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.N.toString()).errorMag(msg).data(data).build();
    }

    public static <T> Result<T> makeSuccess(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).errorMag(msg).build();
    }

    public static <T> Result<T> makeSuccess() {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).build();
    }

    public static <T> Result<T> makeSuccess(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).errorMag(msg).data(data).build();
    }

    public static <T> Result<T> makeSuccess(T data) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).data(data).build();
    }

    public static <T> Result<T> makeWarn(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).errorMag(msg).build();
    }

    public static <T> Result<T> makeWarn() {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).build();
    }

    public static <T> Result<T> makeWarn(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).errorMag(msg).data(data).build();
    }

    public static <T> Result<T> makeWarn(T data) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).data(data).build();
    }

    public static <T> Result<T> makeConfirm(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).errorMag(msg).build();
    }

    public static <T> Result<T> makeConfirm() {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).build();
    }

    public static <T> Result<T> makeConfirm(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).errorMag(msg).data(data).build();
    }

    public static <T> Result<T> makeConfirm(T data) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).data(data).build();
    }

}
