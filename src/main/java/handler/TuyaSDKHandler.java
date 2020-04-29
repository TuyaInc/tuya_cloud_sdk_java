package handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuya.api.client.device.DeviceClient;
import com.tuya.api.client.device.models.PostDeviceCommandReq;
import com.tuya.api.client.openiothub.OpenIotHubClient;
import com.tuya.api.client.openiothub.models.OpenIoThubReq;
import com.tuya.api.common.ClientConfig;
import com.tuya.api.common.RegionEnum;
import com.tuya.api.common.TuyaResult;
import com.tuya.api.exception.TuyaCloudSDKException;
import com.tuya.api.utils.ProtocolAESBase64Util;
import com.tuya.api.utils.SignUtil;
import handler.sdk.input.*;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * 云函数入口类
 */
public class TuyaSDKHandler {

    private String SECRET = null;

    /**
     * 初始化客户端
     *
     * @param region
     */
    private void initClient(RegionEnum region) {
        final String CLIENT_ID = System.getenv("clientID");
        SECRET = System.getenv("clientSecret");
        // 初始化开发者信息，对应涂鸦云AccessId、AccessKey、涂鸦云服务url
        ClientConfig.init(CLIENT_ID, SECRET, region);
    }

    /**
     * 方法入口
     *
     * @param inputParam
     * @return
     */
    public String handler(RequestInput inputParam) {
        initClient(RegionEnum.URL_CN);

        TuyaResult result = null;

        RequestActionEnum action = RequestActionEnum.getActionByCode(inputParam.getAction());
        if (Objects.isNull(action)) {
            System.out.println("Action Not Support" + inputParam.getAction());
            result = new TuyaResult(4001, "Invalid Action");
        } else {
            result = processBiz(action, inputParam);
        }

        //统一处理成json string返回
        return JSON.toJSONString(result);

    }

    /**
     * 处理action
     *
     * @param action
     * @param inputParam
     * @return
     */
    private TuyaResult processBiz(RequestActionEnum action, RequestInput inputParam) {
        try {
            switch (action) {
                case GET_DEVICE_LIST:
                    GetDeviceListParam deviceListParam = inputParam.getParams().toJavaObject(GetDeviceListParam.class);
                    return DeviceClient.getDeviceListByUid(deviceListParam.getUid());
                case CONTROL:
                    PostDeviceCommandReq commandReq = inputParam.getParams().toJavaObject(PostDeviceCommandReq.class);
                    return DeviceClient.postDeviceCommand(commandReq);
                case OPEN_IOT_HUB_CONFIG:
                    OpenIoThubReq configReq = inputParam.getParams().toJavaObject(OpenIoThubReq.class);
                    return OpenIotHubClient.PostOpenIotHubAccessConfig(configReq);
                case MQTT_DATA_CONVERT:
                    MqttReqParam mqttParam = inputParam.getParams().toJavaObject(MqttReqParam.class);
                    return this.convertMqttData(mqttParam);
                default:
                    System.out.println("Action Not Support" + inputParam.getAction());
                    return new TuyaResult(4001, "Invalid Action");
            }
        } catch (TuyaCloudSDKException e) {
            System.out.println("调用出错：" + e);
            return new TuyaResult(e.getCode(), e.getMessage());
        }
    }


    /**
     * 数据加解密转换
     *
     * @param mqttParam
     * @return
     */
    private TuyaResult convertMqttData(MqttReqParam mqttParam) {
        try {
            switch (mqttParam.getType()) {
                case "ENC":
                    return new TuyaResult(true, System.currentTimeMillis(),
                            ProtocolAESBase64Util.encrypt(mqttParam.getData().getData().toString(), SECRET.substring(8, 24)));
                case "DEC":
                    return this.decMqttData(mqttParam.getData(),SECRET);
                default:
                    System.out.println("Data type Not Support" + mqttParam.getType());
                    return new TuyaResult(4002, "data type Action");
            }
        } catch (TuyaCloudSDKException e) {
            System.out.println("调用出错：" + e);
            return new TuyaResult(e.getCode(), e.getMessage());
        } catch (Exception e) {
            System.out.println("exception调用出错：" + e);
            return new TuyaResult(1009, e.getMessage());
        }
    }

    /**
     * mqtt数据解密
     * @return
     */
    private TuyaResult decMqttData(CloudProtocolVO protocolVO,String appSecret) throws Exception {

        if(StringUtils.isBlank(protocolVO.getSign())){
            return new TuyaResult(4003, "sign invalid");
        }

        // 重新计算签名
        String validateSign = SignUtil.getCloudSign(protocolVO, appSecret);

        // 校验签名
        if(!protocolVO.getSign().equals(validateSign)){
            return new TuyaResult(4003, "sign invalid");
        }

        return new TuyaResult(true, System.currentTimeMillis(), JSONObject.parseObject(
                 ProtocolAESBase64Util.decrypt(protocolVO.getData().toString(), appSecret.substring(8, 24))));
    }


}
