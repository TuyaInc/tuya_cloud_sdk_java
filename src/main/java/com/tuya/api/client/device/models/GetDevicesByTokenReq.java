package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 获取配网token下所有设备列表
 */
public class GetDevicesByTokenReq implements ApiRequest {

    /**
     * 云端返回的设备配网token
     */
    @NotBlank
    private String token;

    public GetDevicesByTokenReq(@NotBlank String token) {
        this.token = token;
    }

    public HttpMethod getRequestMethod() {
        return null;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/devices/tokens/%s", this.token);
    }
}
