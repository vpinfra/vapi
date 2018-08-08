package com.vpinfra.core.model;

import java.io.Serializable;
import java.util.Map.Entry;

/**
 * 键值对bean
 *
 * @author 尹俊峰
 * @date 2016年8月22日
 * @since 2.1.1
 */
public class KeyValueBean implements Serializable, Entry<String, String> {

    private static final long serialVersionUID = -3832626162173359411L;

    /**
     * 键名
     */
    private String key;

    /**
     * 键值
     */
    private String value;

    /**
     * 名称
     */
    private String name;

    /**
     * 构造函数
     *
     * @param key 键
     */
    public KeyValueBean(final String key) {
        this.key = key;
    }

    /**
     * 构造函数
     *
     * @param key 键
     * @param value 值
     */
    public KeyValueBean(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String setValue(final String value) {
        this.value = value;
        return this.value;
    }
}
