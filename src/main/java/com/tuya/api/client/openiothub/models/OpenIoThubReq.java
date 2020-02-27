package com.tuya.api.client.openiothub.models;

import com.google.gson.Gson;
import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.ApiRequestBody;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * openIotHub请求
 */
public class OpenIoThubReq implements ApiRequest, ApiRequestBody {

    @NotBlank
    private String uid;

    @NotBlank
    private String uniqueId;

    private String type;

    public OpenIoThubReq(@NotBlank String uid, @NotBlank String uniqueId, String type) {
        this.uid = uid;
        this.uniqueId = uniqueId;
        this.type = type;
    }

    @Override
    public HttpMethod getRequestMethod() {
        return HttpMethod.POST;
    }


    @Override
    public String getRequestUrl() {
        return String.format("/v1.0/open-iot-hub/access/config");
    }

    @Override
    public String getRequestBody() {
        return new Gson().toJson(this);
    }
}