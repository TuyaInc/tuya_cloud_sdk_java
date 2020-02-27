package com.tuya.api.client.openiothub;

import com.tuya.api.client.openiothub.models.OpenIoThubReq;
import com.tuya.api.common.RequestHandler;
import com.tuya.api.common.TuyaResult;

/**
 * OpenIotHub操作客户端类
 */
public class OpenIotHubClient {

    /**
     * 获取openiothub配置信息
     *
     * @param req
     * @return
     */
    public static TuyaResult PostOpenIotHubAccessConfig(OpenIoThubReq req) {
        return RequestHandler.sendRequest(req);
    }

}
