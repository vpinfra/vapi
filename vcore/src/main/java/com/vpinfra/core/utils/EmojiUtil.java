package com.vpinfra.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * emoji 工具类
 *
 * @author 尹俊峰
 * @date 2017年1月21日
 * @since 2.1.1
 */
public final class EmojiUtil {

    private EmojiUtil() {
    }

    /**
     * 检测是否有emoji字符.
     *
     * @param source 等待检测的字符串
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    /**
     * 是否emoji字符串.
     *
     * @param codePoint 等待检测的字符
     * @return 返回是否是 emoji 字符
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤 emoji 或者 其他非文字类型的字符.
     *
     * @param source 等待检测的字符串
     * @return 返回过滤后的结果
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            //如果不包含，直接返回
            return source;
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            }
        }

        if (buf == null) {
            //如果没有找到 emoji表情，则返回源字符串
            return source;
        } else {
            //这里的意义在于尽可能少的toString，因为会重新生成字符串
            if (buf.length() == len) {
                return source;
            } else {
                return buf.toString();
            }
        }

    }
}
