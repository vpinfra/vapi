package com.vpinfra.core.exception;

import com.vpinfra.core.model.ErrorInfo;

/**
 * 基础检查类型异常定义，每一个业务模块都要集成这个基础异常类，同时在业务端进行统一异常处理
 *
 * @author 尹俊峰
 * @date 2017年3月9日
 * @since 2.1.1
 */
public abstract class BaseCheckException extends Exception {

    private static final long serialVersionUID = 7913570275864821983L;

    private ErrorInfo errorInfo;

    /**
     * {@link Exception#Exception(Throwable)}
     */
    public BaseCheckException(Throwable cause) {
        super(cause);
    }

    /**
     * 通过一个详细的信息以及一个 {@link ErrorInfo} 实例创建一个基本的 BaseCheckException
     *
     * @param message
     * @param errorInfo
     */
    public BaseCheckException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    /**
     * 通过一条详细的信息创建一个基本的 BaseCheckException {@link Exception#Exception(String)}
     *
     * @param message 错误相关信息
     */
    public BaseCheckException(String message) {
        super(message);
    }

    /**
     * 通过一个 {@link ErrorInfo} 实例创建一个基本的 BaseCheckException
     *
     * @param errorInfo 错误返回模型
     */
    public BaseCheckException(ErrorInfo errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

}
