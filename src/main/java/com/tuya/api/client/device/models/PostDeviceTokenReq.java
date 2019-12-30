package com.tuya.api.client.device.models;

import com.google.gson.Gson;
import com.tuya.api.common.ApiRequest;
import com.tuya.api.common.ApiRequestBody;
import com.tuya.api.common.HttpMethod;

import javax.validation.constraints.NotBlank;

/**
 * 生成设备配网token请求类
 */
public class PostDeviceTokenReq implements ApiRequest, ApiRequestBody {

    /**
     * 用户唯一标识
     */
    @NotBlank
    private String uid;

    /**
     * 用户所在时区id，州/省份（Asia/Shanghai)
     */
    @NotBlank
    private String timeZoneId;

    /**
     * 经度
     */
    private String lon;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 系统语言，zh,eu等，默认zh
     */
    private String lang = "zh";

    public PostDeviceTokenReq(@NotBlank String uid, @NotBlank String timeZoneId) {
        this.uid = uid;
        this.timeZoneId = timeZoneId;
    }

    public PostDeviceTokenReq(@NotBlank String uid, @NotBlank String timeZoneId, String lon, String lat, String lang) {
        this.uid = uid;
        this.timeZoneId = timeZoneId;
        this.lon = lon;
        this.lat = lat;
        this.lang = lang;
    }

    public HttpMethod getRequestMethod() {
        return HttpMethod.POST;
    }

    public String getRequestUrl() {
        return "/v1.0/devices/token";
    }

    public String getRequestBody() {
        return new Gson().toJson(this);
    }
}