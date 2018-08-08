package com.vpinfra.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * html 通用处理类
 *
 * @author 蒋勤
 * @date 2017年3月10日
 * @since 2.1.1
 */
public final class HtmlUtil {

    /**
     * 定义script的正则表达式
     */
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REGEX_HTML = "<[^>]+>";

    private HtmlUtil() {
    }

    /**
     * 过滤script.
     *
     * @param htmlStr 等待处理的字符串
     * @return
     */
    public static String filterHtml(String htmlStr) {
        Pattern pattern = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        return matcher.replaceAll("");
    }

    /**
     * 过滤style.
     *
     * @param htmlStr 等待处理的字符串
     * @return
     */
    public static String filterStyle(String htmlStr) {
        Pattern pattern = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        return matcher.replaceAll("");
    }

    /**
     * 过滤html.
     *
     * @param htmlStr 等待处理的字符串
     * @return
     */
    public static String filterScript(String htmlStr) {
        Pattern pattern = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        return matcher.replaceAll("");
    }

}