package com.tuya.api.client.token.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

/**
 * 获取token请求类
 */
public class GetTokenReq implements ApiRequest {

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    /**
     * 简单模式
     *
     * @return
     */
    public String getRequestUrl() {
        return "/v1.0/token?grant_type=1";
    }
}
