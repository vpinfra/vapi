package com.vpinfra.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配工具.
 * 
 * TODO 增加常用正则验证
 *
 * @author 尹俊峰
 * @date 2016年8月22日
 * @since 2.1.1
 */
public final class RegexUtil {

    /**
     * email验证正则表达式.
     *
     * 来自于<a href="http://www.regular-expressions.info/email.html">How to Find or Validate an Email Address</a>
     */
    public static final String REGEX_EMAIL = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    /**
     * email正则 {@link Pattern}
     */
    public static final Pattern PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);
    
    /**
     * ip验证正则表达式.
     *
     * 来自于<a href="http://www.regular-expressions.info/ip.html">How to Find or Validate an ip Address</a>
     */
    public static final String REGEX_IP_ADDRESS = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * ip正则 {@link Pattern}
     */
    public static final Pattern PATTERN_IP_ADDRESS = Pattern.compile(REGEX_IP_ADDRESS, Pattern.CASE_INSENSITIVE);
    
    private RegexUtil() {
    }

    /**
     * 验证输入字符串是否为email.
     *
     * @param input
     * @return
     */
    public static boolean isEmail(CharSequence input) {
        return StringUtils.isNotBlank(input) && PATTERN_EMAIL.matcher(input).matches();
    }
    
    /**
     * 验证输入字符串是否为ip.
     *
     * @param input
     * @return
     */
    public static boolean isIp(CharSequence input) {
        return StringUtils.isNotBlank(input) && PATTERN_IP_ADDRESS.matcher(input).matches();
    }
    
    /**
     * 输入是否完全匹配指定的正则表达式.
     *
     * @param input
     * @param regex
     * @return
     */
    public static boolean match(CharSequence input, String regex) {
        Matcher matcher = compile(regex).matcher(input);

        return matcher.matches();
    }

    /**
     * 获取所要捕获的组.
     *
     * @param input
     * @param regex
     * @return 返回的结果中不包含匹配到的字符串，仅包含所有组对应的字符串结果，不为null
     */
    public static List<String> getGroups(CharSequence input, String regex) {
        List<String> list = new ArrayList<String>();
        Matcher matcher = compile(regex).matcher(input);

        while (matcher.find()) {
            // 返回匹配的组数,不包括匹配的字符串本身
            int len = matcher.groupCount();

            for (int i = 1; i <= len; i++) {
                list.add(matcher.group(i));
            }
        }

        return list;
    }

    /**
     * 构建正则表达式.
     *
     * @param regex
     * @return
     */
    private static Pattern compile(String regex) {
        return Pattern.compile(regex);
    }
}
