package com.tuya.api.common;

public interface ApiRequest {

    /**
     * 请求方法
     *
     * @return
     */
    HttpMethod getRequestMethod();

    /**
     * 请求url
     *
     * @return
     */
    String getRequestUrl();
}
