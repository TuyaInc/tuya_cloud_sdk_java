package com.tuya.api.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * token响应类
 */
public class TokenResponse {

    /**
     * access_token
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * refresh_token
     */
    @JSONField(name = "refresh_token")
    private String refreshToken;

    /**
     * 过期时间
     */
    @JSONField(name = "expire_time")
    private long expireTime;

    /**
     * 用户id
     */
    private String uid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
