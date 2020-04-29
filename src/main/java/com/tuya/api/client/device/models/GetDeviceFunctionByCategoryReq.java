package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 根据category获取function列表请求类
 */
public class GetDeviceFunctionByCategoryReq implements ApiRequest {

    @NotBlank
    private String category;

    public GetDeviceFunctionByCategoryReq(String category) {
        this.category = category;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/functions/%s", this.category);
    }
}
