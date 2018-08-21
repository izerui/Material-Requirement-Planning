package com.ierp2.mrp.configuration.web;

import io.swagger.annotations.ApiModelProperty;

public class Response<T> {

    /**
     * 请求处理是否成功
     */
    private boolean success;

    /**
     * 错误编码
     */
    private String errCode;

    /**
     * 错误消息
     */
    private String errMsg;

    /**
     * 响应内容实体
     */
    private T data;

    @ApiModelProperty(hidden = true)
    private Exception exception;

    protected Response() {
    }

    protected Response(boolean success, String errCode, String errMsg, T data) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    protected Response(boolean success, String errCode, String errMsg, T data, Exception exception) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
        this.exception = exception;
    }

    /**
     * 成功
     */
    public static Response success() {
        return new Response<>(true, null, null, null);
    }

    /**
     * 成功
     */
    public static <T> Response success(T data) {
        return new Response<>(true, null, null, data);
    }

    /**
     * 失败
     */
    public static Response error(String errCode, String errMsg) {
        return new Response<>(false, errCode, errMsg, null);
    }

    /**
     * 失败
     */
    public static <T> Response<T> error(String errCode, String errMsg, Exception exception) {
        return new Response<>(false, errCode, errMsg, null, exception);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public T getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
