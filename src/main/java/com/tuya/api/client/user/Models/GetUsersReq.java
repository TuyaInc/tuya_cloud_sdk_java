package com.tuya.api.client.user.Models;

import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.HttpMethod;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 获取用户请求类
 */
public class GetUsersReq implements ApiRequest {

    /**
     * 渠道标识符
     */
    @NotBlank
    private String schema;

    /**
     * 当前页
     */
    @NotNull
    @Min(1)
    private int pageNo;

    /**
     * 每页大小
     */
    @NotNull
    private int pageSize;

    public GetUsersReq(String schema, int pageNo, int pageSize) {
        this.schema = schema;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.GET;
    }

    public String getRequestUrl() {
        return String.format("/v1.0/apps/%s/users?page_no=%s&page_size=%s", this.schema, this.pageNo, this.pageSize);
    }
}
