package com.vpinfra.core.enums;

/**
 *  通用异常处理枚举接口定义
 *
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018/8/6
 */
public interface BaseErrorCodeEnum<C, N>{

    C getCode();

    N getName();
}
