package com.tuya.api.client.device;

import com.tuya.api.client.device.models.*;
import com.tuya.api.common.RequestHandler;
import com.tuya.api.common.TuyaResult;

/**
 * 设备操作客户端类
 */
public class DeviceClient {


    /**
     * 获取设备信息
     *
     * @param deviceId
     * @return
     */
    public static TuyaResult getDevice(String deviceId) {
        return RequestHandler.sendRequest(new GetDeviceReq(deviceId));
    }

    /**
     * 获取设备支持的function列表
     *
     * @param deviceId
     * @return
     */
    public static TuyaResult getDeviceFunctions(String deviceId) {
        return RequestHandler.sendRequest(new GetDeviceFunctionsReq(deviceId));
    }

    /**
     * 根据category获取function列表
     *
     * @param category
     * @return
     */
    public static TuyaResult getDeviceFunctionByCategory(String category) {
        return RequestHandler.sendRequest(new GetDeviceFunctionByCategoryReq(category));
    }

    /**
     * 获取设备功能点的信息
     *
     * @param deviceId
     * @return
     */
    public static TuyaResult getDeviceStatus(String deviceId) {
        return RequestHandler.sendRequest(new GetDeviceStatusReq(deviceId));
    }


    /**
     * 批量获取设备状态
     *
     * @param deviceId
     * @return
     */
    public static TuyaResult getDeviceList(String deviceId) {
        return RequestHandler.sendRequest(new GetDeviceListReq(deviceId));
    }

    /**
     * 设备指令下发
     *
     * @param req
     * @return
     */
    public static TuyaResult PostDeviceCommand(PostDeviceCommandReq req) {
        return RequestHandler.sendRequest(req);
    }

    /**
     * 移除设备
     *
     * @param deviceId
     * @return
     */
    public static TuyaResult deleteDevice(String deviceId) {
        return RequestHandler.sendRequest(new DeleteDeviceReq(deviceId));
    }

    /**
     * 根据用户id获取设备列表
     *
     * @param uid
     * @return
     */
    public static TuyaResult getDeviceListByUid(String uid) {
        return RequestHandler.sendRequest(new GetDeviceListByUidReq(uid));
    }

    /**
     * 生成设备配网token
     *
     * @param req
     * @return
     */
    public static TuyaResult generateDeviceToken(PostDeviceTokenReq req) {
        return RequestHandler.sendRequest(req);
    }

    /**
     * 获取配网token下所有设备列表
     *
     * @param token 云端返回的设备配网token
     * @return
     */
    public static TuyaResult getDevicesByToken(String token) {
        return RequestHandler.sendRequest(new GetDevicesByTokenReq(token));
    }
}
