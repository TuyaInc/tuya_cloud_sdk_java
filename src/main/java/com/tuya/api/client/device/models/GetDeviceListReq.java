package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 批量获取设备状态请求类
 */
public class GetDeviceListReq implements ApiRequest {

    @NotBlank
    private String deviceId;

    public GetDeviceListReq(String deviceId) {
        this.deviceId = deviceId;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/devices/status?device_ids=%s", this.deviceId);
    }
}
