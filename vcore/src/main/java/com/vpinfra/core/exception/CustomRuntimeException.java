package com.vpinfra.core.exception;

import com.vpinfra.core.model.ErrorInfo;

/**
 * 自定义非检查异常异常，异常将被抛出，可能进程挂掉，慎用
 *
 * @author 尹俊峰
 * @date 2017年3月13日
 * @since 2.1.1
 */
public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 3303354290272912227L;

    private ErrorInfo errorInfo;

    /**
     * {@link RuntimeException#RuntimeException()}
     */
    public CustomRuntimeException() {
        super();
    }

    /**
     * {@link RuntimeException#RuntimeException(Throwable)}
     */
    public CustomRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * 通过一个 {@link ErrorInfo} 实例创建一个基本的 CustomRuntimeException
     *
     * @param errorInfo
     */
    public CustomRuntimeException(ErrorInfo errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
    }
    
    /**
     * 通过一个 {@link ErrorInfo} 实例创建一个基本的 CustomRuntimeException
     * 
     * @param errorInfo
     * @param cause
     */
    public CustomRuntimeException(ErrorInfo errorInfo, Throwable cause) {
        super(errorInfo.getMessage(), cause);
        this.errorInfo = errorInfo;
    }

    /**
     * 通过一个详细的信息以及一个 {@link ErrorInfo} 实例创建一个基本的 CustomRuntimeException
     *
     * @param message
     * @param errorInfo
     */
    public CustomRuntimeException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }
    
    /**
     * 通过一个详细的信息创建一个基本的 CustomRuntimeException
     *
     * @param message
     */
    public CustomRuntimeException(String message) {
        super(message);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

}
