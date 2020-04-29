package com.tuya.api;

import com.tuya.api.common.ClientConfig;
import com.tuya.api.common.RegionEnum;
import org.junit.Test;

public class TokenClientTest {

    // 对应涂鸦云AccessId、AccessKey
    private static final String accessId = "xxxxx";
    private static final String accessKey = "xxxxx";

    @Test
    public void test() {
        try {
            // 初始化开发者信息，对应涂鸦云AccessId、AccessKey、涂鸦云服务url
            ClientConfig.init(accessId, accessKey, RegionEnum.URL_CN);

//            // 同步用户示例
//            SyncUserVO vo = new SyncUserVO("86", "17265439876", "17265439876", "1231231", 1);
//            UserClient.syncUser(schema, vo);


//            // 查询用户列表示例
//            String schema = "xxxx";
//            TuyaResult result = UserClient.getUsers(schema,1,3);
//            System.out.println(result.getResult());



//            String deviceId = "vdevo123131xxxxxx";

//            // 获取设备信息示例
//            TuyaResult result = DeviceClient.getDevice(deviceId);
//            System.out.println(result);
//
//            // 获取设备支持的function列表示例
//            TuyaResult result = DeviceClient.getDeviceFunctions(deviceId);
//            System.out.println(result);



//            String category = "cz";
//
//            // 根据category获取function列表示例
//            TuyaResult result = DeviceClient.getDeviceFunctionByCategory(category);
//            System.out.println(result);
//
//            // 获取设备功能点的信息示例
//            TuyaResult result = DeviceClient.getDeviceStatus(deviceId);
//            System.out.println(result);
//
//            // 批量获取设备状态示例
//            TuyaResult result = DeviceClient.getDeviceList(deviceId);
//            System.out.println(result);
//
//            // 设备指令下发示例
//            List<CodeValuePair> list = new ArrayList<CodeValuePair>(){{add(new CodeValuePair("switch_led", true));
//            add(new CodeValuePair("bright", 30));}};
//            PostDeviceCommandReq req = new PostDeviceCommandReq(deviceId, list);
//            TuyaResult result = DeviceClient.PostDeviceCommand(req);
//            System.out.println(result);


//            String uid = "12312323432ui";
//
//            // 生成设备配网token示例
//            TuyaResult result = DeviceClient.generateDeviceToken(new PostDeviceTokenReq(uid, "Asia/Shanghai"));
//            System.out.println(result);
//
//            // 移除设备示例
//            TuyaResult result = DeviceClient.deleteDevice(deviceId);
//            System.out.println(result);

//            // 通用接口示例
//            SyncUserVO vo = new SyncUserVO("86", "test1230@163.com", "test1230@163.com", "1231231", 2);
//            TuyaResult result = CommonClient.sendRequest("/v1.0/apps/xxx/user", HttpMethod.POST, null, vo);
//            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
