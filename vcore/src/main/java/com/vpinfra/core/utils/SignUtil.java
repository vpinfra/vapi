package com.vpinfra.core.utils;

import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 签名工具类.
 *
 * @author 尹俊峰
 * @date 2017年6月27日
 * @since 2.1.1
 */
public class SignUtil {

    private static LogUtil logger = LogUtil.newInstance(SignUtil.class);

    private SignUtil() {
        
    }
    /**
     * 生成签名.
     *
     * @param paramMap 参数map，参数值为String
     * @param uri 请求相对路径：“user/add”
     * @param privateKey 签名私钥
     * @param apiTime 时间戳
     * @return
     */
    public static String generateSignWithParamStr(Map<String, String> paramMap, 
            String uri, String privateKey, String apiTime) {
        String signKey = null;
        Map<String, String> map = new TreeMap<>(
                Comparator.naturalOrder());
        map.putAll(paramMap);
        StringBuilder signStr = new StringBuilder();
        for (Entry<String, String> entry : map.entrySet()) {
            signStr.append(String.valueOf(entry.getValue()));
        }
        if (uri.startsWith("/")) {
            uri = uri.substring(0);
        }
        signStr.append(uri);
        signStr.append(privateKey);
        signStr.append(apiTime);

        try {
            logger.debug(String.format("[unencrypted] %s", signStr.toString()));
            signKey = CryptUtil.md5HexStr(signStr.toString()).toLowerCase();
            logger.debug(String.format("[encrypted] %s", signKey));
        } catch (NoSuchAlgorithmException e) {
            logger.error("参数加密错误", e);
        }
        return signKey;
    }
    
    /**
     * @param apiSecret
     * @param paramMap
     * @param uri
     * @param nonce
     * @param apiTime
     * @return
     */
    public static String generateSignWithParamStr(String apiSecret, Map<String, String> paramMap, String uri, String nonce, String apiTime) {
        String signKey = null;
        // 降序排序
        Map<String, String> map = new TreeMap<>(String::compareTo);
        map.putAll(paramMap);
        StringBuilder signStr = new StringBuilder();

        if (!uri.startsWith("/")) {
            uri = "/" + uri;
        }

        signStr
                .append(nonce).append("|")
                .append(apiSecret).append("|")
                .append(apiTime).append("|")
                .append(uri).append("|");

        StringBuilder signParams = new StringBuilder();

        for(Entry<String, String> entry : map.entrySet()) {

            if (signParams.length() == 0) {
                signParams
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());
            } else {
                signParams
                        .append("&")
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());
            }
        }

        signStr.append(signParams);

        logger.debug(String.format("[unencrypted] %s", signStr.toString()));

        try {
            signKey = CryptUtil.md5HexStr(signStr.toString()).toLowerCase();
            logger.debug(String.format("[encrypted] %s", signKey));
        } catch (NoSuchAlgorithmException e) {
            logger.error("参数加密错误", e);
        }
        return signKey;
    }

}
