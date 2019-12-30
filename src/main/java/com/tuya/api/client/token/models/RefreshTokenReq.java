package com.tuya.api.client.token.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;
import com.tuya.api.common.TokenCache;

/**
 * 刷新token请求类
 */
public class RefreshTokenReq implements ApiRequest {

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/token/%s", TokenCache.cache.getIfPresent(TokenCache.REFRESH_TOKEN));
    }
}
