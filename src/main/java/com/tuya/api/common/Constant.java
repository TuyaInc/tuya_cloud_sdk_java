package com.tuya.api.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 常量类
 */
public class Constant {

    /**
     * 开发者账号
     */
    public static final String ACCESS_ID = "accessId";

    /**
     * 开发者密钥
     */
    public static final String ACCESS_KEY = "accessKey";

    /**
     * 涂鸦云域名
     */
    public static final String ENDPOINT = "endpoint";

    /**
     * 用于存储开发者信息
     */
    public static final Map<String, String> map = new ConcurrentHashMap<String, String>();
}
