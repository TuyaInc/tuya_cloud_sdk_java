package handler.sdk.input;

/**
 * 请求参数枚举类
 */
public enum RequestActionEnum {
    GET_DEVICE_LIST("GetDeviceList", "获取用户设备列表"),
    CONTROL("Control", "控制设备"),
    OPEN_IOT_HUB_CONFIG("GetOpenIotHubConfig", "连接配置信息"),
    MQTT_DATA_CONVERT("ConvertMqttData", "数据转换"),
    ;

    private String code;
    private String desc;

    RequestActionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public static RequestActionEnum getActionByCode(String code) {
        for (RequestActionEnum each : RequestActionEnum.values()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

}
