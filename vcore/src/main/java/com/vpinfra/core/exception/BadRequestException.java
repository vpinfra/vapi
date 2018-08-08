package com.vpinfra.core.exception;

import com.vpinfra.core.enums.BaseErrorCodeEnum;
import com.vpinfra.core.model.ErrorInfo;

import java.util.Map;

/**
 *  请求失败异常类
 *
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018/8/6
 */
public class BadRequestException extends CustomRuntimeException{

    private static final long serialVersionUID = -8729914328575485695L;

    private ErrorInfo errorInfo;

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(ErrorInfo errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
    }

    public BadRequestException(BaseErrorCodeEnum<Integer, String> codeEnum) {
        super(ErrorInfo.builder().code(codeEnum.getCode()).message(codeEnum.getName()).build());
    }

    public BadRequestException(BaseErrorCodeEnum<Integer, String> codeEnum, Map<String, Object> extraParam) {
        super(ErrorInfo.builder().code(codeEnum.getCode()).message(codeEnum.getName()).extraParam(extraParam).build());
    }

    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

}
