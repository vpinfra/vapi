package com.vpinfra.core.exception;

import com.vpinfra.core.model.ErrorInfo;

/**
 * 客户端检查异常
 *
 * @author 尹俊峰
 * @date 2017年6月13日
 * @since 2.1.1
 */
public class CustomCheckException extends BaseCheckException {

    private static final long serialVersionUID = 7446679681377837413L;

    /**
     * 通过一个 {@link ErrorInfo} 实例创建一个基本的 CustomCheckException {@link BaseCheckException#BaseCheckException(ErrorInfo)}
     *
     * @param errorInfo
     */
    public CustomCheckException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    /**
     * 通过一条详细的信息创建一个基本的 CustomerCheckException {@link BaseCheckException#BaseCheckException(String)}
     *
     * @param message
     */
    public CustomCheckException(String message) {
        super(message);
    }

}
