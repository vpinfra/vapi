package com.vpinfra.core.utils;

import java.util.UUID;

/**
 * 随机数生成工具.
 *
 * @author 尹俊峰
 * @date 2016年6月26日
 * @since 2.1.1
 */
public final class RandomUtil {

    private RandomUtil() {
    }

    /**
     * 生成指定位数的随机数.
     *
     * @param length 随机数长度
     * @return
     */
    public static String getUUID(int length) {
        String result = UUID.randomUUID().toString();
        result = result.replaceAll("-", "");

        if (0 < length && result.length() >= length) {
            result = result.substring(0, length - 1);
        }

        return result;
    }
}
