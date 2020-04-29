package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 获取设备信息请求类
 */
public class GetDeviceReq implements ApiRequest {

    @NotBlank
    private String deviceId;

    public GetDeviceReq(String deviceId) {
        this.deviceId = deviceId;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/devices/%s", this.deviceId);
    }
}
