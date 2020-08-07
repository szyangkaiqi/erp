package com.cloud.erp.pojo.vo;

import java.io.Serializable;

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

    @ApiModelProperty(value = "返回值")
    private T data;

    public Result() {

    }

    public Result<T> makeFail(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.N.toString()).errorMag(msg).build();
    }

    public Result<T> makeFail(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.N.toString()).errorMag(msg).data(data).build();
    }

    public Result<T> makeSuccess(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).errorMag(msg).build();
    }

    public Result<T> makeSuccess() {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).build();
    }

    public Result<T> makeSuccess(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).errorMag(msg).data(data).build();
    }

    public Result<T> makeSuccess(T data) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.Y.toString()).data(data).build();
    }

    public Result<T> makeWarn(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).errorMag(msg).build();
    }

    public Result<T> makeWarn() {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).build();
    }

    public Result<T> makeWarn(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).errorMag(msg).data(data).build();
    }

    public Result<T> makeWarn(T data) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.W.toString()).data(data).build();
    }

    public Result<T> makeConfirm(String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).errorMag(msg).build();
    }

    public Result<T> makeConfirm() {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).build();
    }

    public Result<T> makeConfirm(T data, String msg) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).errorMag(msg).data(data).build();
    }

    public Result<T> makeConfirm(T data) {
        return Result.<T>builder().errorFlag(BaseConstant.ERROR_FLAG.C.toString()).data(data).build();
    }

}
