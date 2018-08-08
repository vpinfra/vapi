package com.vpinfra.core.response;

import com.google.gson.annotations.SerializedName;

/**
 * 通用响应基类
 *
 * @author 尹俊峰
 * @date 2016年7月18日
 * @since 2.1.1
 */
public class BaseResponse<T> {

    /**
     * 响应业务码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应数据
     */
    @SerializedName("extra_param")
    private Object extraParam;

    /**
     * 构造 BasePageResponse 的静态方法
     *
     * <pre>
     * BaseResponse.builder().data("some data..").code(200)
     * </pre>
     *
     * @return BasePageResponse
     */
    public static BaseResponse builder() {
        return new BaseResponse();
    }

    public BaseResponse build() {
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public BaseResponse code(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse message(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse data(T data) {
        this.data = data;
        return this;
    }

    public Object getExtraParam() {
        return extraParam;
    }

    public BaseResponse extraParam(Object extraParam) {
        this.extraParam = extraParam;
        return this;
    }
}
