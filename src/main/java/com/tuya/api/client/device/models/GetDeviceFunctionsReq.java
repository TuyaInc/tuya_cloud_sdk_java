package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 获取设备支持的function列表请求类
 */
public class GetDeviceFunctionsReq implements ApiRequest {

    @NotBlank
    private String deviceId;

    public GetDeviceFunctionsReq(String deviceId) {
        this.deviceId = deviceId;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/devices/%s/functions", this.deviceId);
    }
}
