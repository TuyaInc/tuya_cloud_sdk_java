package com.tuya.api.client.user;

import com.tuya.api.client.user.Models.GetUsersReq;
import com.tuya.api.client.user.Models.SyncUserReq;
import com.tuya.api.client.user.Models.SyncUserVO;
import com.tuya.api.common.RequestHandler;
import com.tuya.api.common.TuyaResult;

/**
 * 用户客户端类
 */
public class UserClient {

    /**
     * 同步用户
     *
     * @param schema 渠道标识符
     * @param user 用户数据
     * @return
     */
    public static TuyaResult syncUser(String schema, SyncUserVO user) {
        return RequestHandler.sendRequest(new SyncUserReq(schema, user));
    }

    /**
     * 获取用户
     *
     * @param schema 渠道标识符
     * @param pageNo 当前页, 从1开始
     * @param pageSize 每页大小
     * @return
     */
    public static TuyaResult getUsers(String schema, int pageNo, int pageSize) {
        return RequestHandler.sendRequest(new GetUsersReq(schema, pageNo, pageSize));
    }
}
