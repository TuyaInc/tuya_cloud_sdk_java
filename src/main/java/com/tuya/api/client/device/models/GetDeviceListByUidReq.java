package com.tuya.api.client.device.models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 根据用户id获取设备列表请求类
 */
public class GetDeviceListByUidReq implements ApiRequest {

    @NotBlank
    private String uid;

    public GetDeviceListByUidReq(String uid) {
        this.uid = uid;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/users/%s/devices", this.uid);
    }
}
