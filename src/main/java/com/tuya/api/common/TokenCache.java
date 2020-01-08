package com.tuya.api.common;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tuya.api.client.token.TokenClient;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

/**
 * token缓存
 */
public class TokenCache {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String EXPIRE_TIME = "expire_time";


    public static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .concurrencyLevel(10)
            .recordStats()
            .build();

    /**
     * 从缓存获取token
     *
     * @return
     */
    public static synchronized String getToken() {
        String accessToken = cache.getIfPresent(ACCESS_TOKEN);
        if (StringUtils.isNotBlank(accessToken)) {
            try {
                // 缓存命中，过期时间在30s后，直接返回，否则需要刷新
                long expireTime = Long.valueOf(cache.getIfPresent(EXPIRE_TIME));
                if (expireTime > (new DateTime().getMillis()/1000 + 30)) {
                    return accessToken;
                }

                TokenClient.refreshToken();
            } catch (Exception e) {
                // 刷新失败，重新获取
                TokenClient.getToken();
            }
        } else {
            // 未命中缓存，获取token
            TokenClient.getToken();
        }

        return cache.getIfPresent(ACCESS_TOKEN);
    }


    public static synchronized void setToken(TuyaResult tr) {
        if (null != tr && null != tr.getResult()) {
            TokenResponse resp = JSONObject.parseObject(JSONObject.toJSONString(tr.getResult()), TokenResponse.class);
            cache.put(ACCESS_TOKEN, resp.getAccessToken());
            cache.put(REFRESH_TOKEN, resp.getRefreshToken());
            cache.put(EXPIRE_TIME, String.valueOf(new DateTime().getMillis()/1000 + resp.getExpireTime()));
        }
    }

}
