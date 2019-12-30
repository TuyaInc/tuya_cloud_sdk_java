package com.tuya.api.common;

/**
 * 涂鸦云各地区服务url
 */
public enum RegionEnum {
    /**
     * 中国区 - CHINA
     */
    URL_CN("https://openapi.tuyacn.com"),

    /**
     * 美洲区 - USA
     */
    URL_US("https://openapi.tuyaus.com"),

    /**
     * 欧洲区 - EUROPE
     */
    URL_EU("https://openapi.tuyaeu.com"),

    /**
     * 印度区 - INDIA
     */
    URL_IN("https://openapi.tuyain.com");

    private String endpoint;

    RegionEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
