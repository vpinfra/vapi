package com.vpinfra.core.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * 错误返回模型
 *
 * @author 尹俊峰
 * @date 2017年3月28日
 * @since 2.1.1
 */
public class ErrorInfo implements Serializable {

    public static final int SYS_ERROR = 500;
    
    private static final long serialVersionUID = -5318560134107301674L;

    /**
     * 错误的标志码
     */
    private Integer code;

    /**
     * 错误的描述信息,如果需要传递format参数，则用 # # 分隔
     */
    private String message;

    /**
     * 额外的参数信息,默认替换 message 中 #test# 参数
     */
    @SerializedName("extra_param")
    private Map<String, Object> extraParam;

    private transient Boolean needFormat = false;

    public static ErrorInfo builder() {
        return new ErrorInfo();
    }

    public ErrorInfo build() {
        return this;
    }

    public ErrorInfo code(Integer code) {
        this.code = code;
        return this;
    }

    public ErrorInfo message(String message) {
        this.message = message;
        return this;
    }

    public ErrorInfo extraParam(Map<String, Object> extraParam) {
        this.extraParam = extraParam;
        return this;
    }

    public ErrorInfo needFormat(boolean needFormat) {
        this.needFormat = needFormat;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        if(null != extraParam && needFormat) {
            extraParam.forEach((k, v) ->{
                String key = "#" + k + "#";
                if(message.contains(key)) {
                    message = message.replaceAll(key, String.valueOf(v));
                }
            });
        }
        return message;
    }

    public Map<String, Object> getExtraParam() {
        return extraParam;
    }

    public Boolean getNeedFormat() {
        return needFormat;
    }
}
