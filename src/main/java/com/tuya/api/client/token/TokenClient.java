package com.tuya.api.client.token;

import com.tuya.api.common.TokenCache;
import com.tuya.api.common.RequestHandler;
import com.tuya.api.common.TuyaResult;
import com.tuya.api.client.token.models.GetTokenReq;
import com.tuya.api.client.token.models.RefreshTokenReq;

/**
 * token客户端类
 */
public class TokenClient {

    /**
     * 获取token
     *
     * @return
     * @throws Exception
     */
    public static TuyaResult getToken() {
        TuyaResult result = RequestHandler.sendRequest(new GetTokenReq(), Boolean.FALSE);
        TokenCache.setToken(result);
        return result;
    }


    /**
     * 刷新token
     *
     * @return
     * @throws Exception
     */
    public static TuyaResult refreshToken() {
        TuyaResult result = RequestHandler.sendRequest(new RefreshTokenReq(), Boolean.FALSE);
        TokenCache.setToken(result);
        return result;
    }
}
