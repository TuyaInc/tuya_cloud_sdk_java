package com.tuya.api.common;

/**
 * 开发者信息配置类
 */
public class ClientConfig {

    /**
     * 配置开发者必要信息
     *
     * @param accessId
     * @param accessKey
     * @param region
     */
    public static void init(String accessId, String accessKey, RegionEnum region) {
        Constant.map.put(Constant.ACCESS_ID, accessId);
        Constant.map.put(Constant.ACCESS_KEY, accessKey);
        Constant.map.put(Constant.ENDPOINT, region.getEndpoint());
    }
}
