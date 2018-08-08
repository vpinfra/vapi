package com.vpinfra.core.utils;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.vpinfra.core.common.Constant;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * json 处理工具类.
 *
 * @author 尹俊峰
 * @date 2017年3月17日
 * @since 2.1.1
 */
public final class JsonUtil {

    private static Gson getJson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .setDateFormat(Constant.DEFAULT_DATE_FORMAT).create();
    }

    /**
     * Object对象转换成json字符串.
     *
     * @param obj 被转换对象
     * @return json字符串
     */
    public static String toJsonString(Object obj) {
        return getJson().toJson(obj);
    }

    /**
     * json 字符串转换成为 Json 对象.
     *
     * @param json 目标json串
     * @return json对象
     */
    public static JsonObject toJsonObject(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject();
    }

    /**
     * Object 转换成为 Json 对象.
     *
     * @param obj 对象
     * @return json对象
     */
    public static JsonObject toJsonObject(Object obj) {
        return getJson().toJsonTree(obj).getAsJsonObject();
    }

    /**
     * Object 转换成为 Json 对象.
     *
     * @param obj 对象
     * @return json对象
     */
    public static JsonElement toJsonElement(Object obj) {
        return getJson().toJsonTree(obj);
    }

    /**
     * json 字符串转换成为 Json 数组对象.
     *
     * @param json 格式字符串
     * @return Json数组
     */
    public static JsonArray toJsonArray(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonArray();
    }

    /**
     * Object 转换成为 Json 数组对象.
     *
     * @param obj 目标对象
     * @return Json数组
     */
    public static JsonArray toJsonArray(Object obj) {
        return getJson().toJsonTree(obj).getAsJsonArray();
    }

    /**
     * json 字符串转换成为指定对象.
     *
     * @param json json格式字符串
     * @param clazz 指定类型对象
     * @return 指定类型对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return getJson().fromJson(json, clazz);
    }

    /**
     * JsonElement 转换成为指定对象.
     *
     * @param json JsonElement
     * @param type 指定类型对象
     * @return 指定类型对象
     */
    public static <T> T toObject(String json, TypeToken<T> type) {
        return getJson().fromJson(json, type.getType());
    }

    /**
     * JsonElement 转换成为指定对象.
     *
     * @param element JsonElement
     * @param clazz 指定类型
     * @return 指定类型对象
     */
    public static <T> T toObject(JsonElement element, Class<T> clazz) {
        return toObject(element.toString(), clazz);
    }

    /**
     * JsonElement 转换成为指定对象.
     *
     * @param element JsonElement
     * @param type 指定类型
     * @return 指定类型对象
     */
    public static <T> T toObject(JsonElement element, TypeToken<T> type) {
        return toObject(element.toString(), type);
    }

    /**
     * JsonObject 转换成为指定对象.
     *
     * @param jsonObj JsonObject
     * @param clazz 指定类型对象
     * @return 指定类型对象
     */
    public static <T> T toObject(JsonObject jsonObj, Class<T> clazz) {
        return toObject(jsonObj.toString(), clazz);
    }

    /**
     * JsonObject 转换成为指定对象.
     *
     * @param jsonObj JsonObject
     * @param type 指定类型对象
     * @return 指定类型对象
     */
    public static <T> T toObject(JsonObject jsonObj, TypeToken<T> type) {
        return toObject(jsonObj.toString(), type);
    }

    /**
     * json 字符串转换成为map.
     *
     * @param json 格式字符串
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(String json) {
        return getJson().fromJson(json, new TypeToken<Map<K, V>>() {
        }.getType());
    }

    /**
     * json字符串转换成为map.
     *
     * @param json 格式字符串
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(String json, Type type) {
        return getJson().fromJson(json, type);
    }

    /**
     * 转换为 {@code Map<string,string>}.
     *
     * @param json
     * @return
     */
    public static Map<String, String> toStringMap(String json) {
        Map<String, String> paramMap = Maps.newHashMap();
        JsonObject jObj = JsonUtil.toJsonObject(json);
        for (Map.Entry<String, JsonElement> entry : jObj.entrySet()) {
            if (entry.getValue() instanceof JsonArray) {
                paramMap.put(entry.getKey(), entry.getValue().getAsJsonArray().toString());
            } else if (entry.getValue() instanceof JsonObject) {
                paramMap.put(entry.getKey(), entry.getValue().getAsJsonObject().toString());
            } else {
                paramMap.put(entry.getKey(), entry.getValue().getAsString());
            }
        }
        return paramMap;
    }

    /**
     * JsonObject转换成为map.
     *
     * @param element json元素
     * @return {@code Map<K, V>}
     */
    public static <K, V> Map<K, V> toMap(JsonElement element) {
        return toMap(element.toString());
    }

    /**
     * JsonObject转换成为map.
     *
     * @param jsonObj json对象
     * @return {@code Map<K, V>}
     */
    public static <K, V> Map<K, V> toMap(JsonObject jsonObj) {
        return toMap(jsonObj.toString());
    }

    /**
     * json字符串转换成为list.
     *
     * @param json 格式字符串
     * @return List<T>
     */
    public static <T> List<T> toList(String json) {
        return getJson().fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * json字符串转换成为list.
     *
     * @param json 格式字符串
     * @param type 返回值类型
     * @return List<T>
     */
    public static <T> List<T> toList(String json, TypeToken<List<T>> type) {
        return getJson().fromJson(json, type.getType());
    }

    /**
     * JsonElement转list.
     *
     * @param element json元素
     * @return {@code List<T>}
     */
    public static <T> List<T> toList(JsonElement element) {
        return toList(element.toString());
    }

    /**
     * JsonElement转list.
     *
     * @param element json元素
     * @param type 返回值类型
     * @return List<T>
     */
    public static <T> List<T> toList(JsonElement element, TypeToken<List<T>> type) {
        return toList(element.toString(), type);
    }

    /**
     * JsonObject转list.
     *
     * @param jsonObj json对象
     * @return {@code List<T>}
     */
    public static <T> List<T> toList(JsonObject jsonObj) {
        return toList(jsonObj.toString());
    }

    /**
     * JsonObject转list.
     *
     * @param jsonObj json对象
     * @param type 返回值类型
     * @return List<T>
     */
    public static <T> List<T> toList(JsonObject jsonObj, TypeToken<List<T>> type) {
        return toList(jsonObj.toString(), type);
    }

    /**
     * get JsonObject from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return JsonObject
     */
    public static JsonObject getJsonObject(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsJsonObject() : null;
    }

    /**
     * get JsonArray from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return JsonArray
     */
    public static JsonArray getJsonArray(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsJsonArray() : null;
    }

    /**
     * get String from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return String
     */
    public static String getString(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsString() : null;
    }

    /**
     * get Boolean from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return Boolean
     */
    public static Boolean getBoolean(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsBoolean() : null;
    }

    /**
     * get Integer from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return Integer
     */
    public static Integer getInt(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsInt() : null;
    }

    /**
     * get Short from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return Short
     */
    public static Short getShort(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsShort() : null;
    }

    /**
     * get Double from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return Double
     */
    public static Double getDouble(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsDouble() : null;
    }

    /**
     * get Float from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return Float
     */
    public static Float getFloat(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsFloat() : null;
    }

    /**
     * get Byte from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return Byte
     */
    public static Byte getByte(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsByte() : null;
    }

    /**
     * get Character from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return Character
     */
    public static Character getChar(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsCharacter() : null;
    }

    /**
     * get BigDecimal from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsBigDecimal() : null;
    }

    /**
     * get BigInteger from JsonObject.
     *
     * @param jsonObj
     * @param memberName
     * @return BigInteger
     */
    public static BigInteger getBigInteger(JsonObject jsonObj, String memberName) {
        return null != jsonObj.get(memberName) ? jsonObj.get(memberName).getAsBigInteger() : null;
    }

    /**
     * 判断是否是json.
     *
     * @param json
     * @return
     */
    public static boolean isJson(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }
}
