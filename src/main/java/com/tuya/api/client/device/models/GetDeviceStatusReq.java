package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 	获取设备功能点的信息请求类
 */
public class GetDeviceStatusReq implements ApiRequest {

    @NotBlank
    private String deviceId;

    public GetDeviceStatusReq(String deviceId) {
        this.deviceId = deviceId;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/devices/%s/status", this.deviceId);
    }
}
