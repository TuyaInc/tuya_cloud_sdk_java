package com.tuya.api.client.device.models;

import com.google.gson.Gson;
import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.ApiRequestBody;
import com.tuya.api.common.HttpMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 设备指令下发请求类
 */
public class PostDeviceCommandReq implements ApiRequest, ApiRequestBody {

    @NotBlank
    private String deviceId;

    @NotNull
    private List<CodeValuePair> commands;

    public PostDeviceCommandReq(@NotBlank String deviceId,
                                @NotNull @Valid List<CodeValuePair> commands) {
        this.deviceId = deviceId;
        this.commands = commands;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.POST;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/devices/%s/commands", this.deviceId);
    }

    public String getRequestBody() {
       return new Gson().toJson(this);
    }
}
