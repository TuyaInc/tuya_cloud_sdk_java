package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 移除设备请求类
 */
public class DeleteDeviceReq implements ApiRequest {

    @NotBlank
    private String deviceId;

    public DeleteDeviceReq(String deviceId) {
        this.deviceId = deviceId;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.DELETE;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/devices/%s", this.deviceId);
    }
}
