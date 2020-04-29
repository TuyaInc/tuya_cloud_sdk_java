package com.tuya.api.client.user.Models;

import com.google.gson.Gson;
import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.ApiRequestBody;
import com.tuya.api.common.HttpMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 同步用户请求类
 */
public class SyncUserReq implements ApiRequest, ApiRequestBody {

    /**
     * 渠道标识符
     */
    @NotBlank
    private String schema;

    /**
     * 用户
     */
    @NotNull
    @Valid
    private SyncUserVO user;

    public SyncUserReq(String schema, SyncUserVO user) {
        this.schema = schema;
        this.user = user;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.POST;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/apps/%s/user", this.schema);
    }

    public String getRequestBody() {
        return new Gson().toJson(this.user);
    }
}
