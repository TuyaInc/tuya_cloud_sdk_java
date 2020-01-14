# 涂鸦云云对接API SDK Java版本

## 使用前需要做

1. 确认JDK7版本及以上

2. 在涂鸦云平台注册开发者账号，确定AccessID、AccessKey、Endpoint(调用地址)这些值

## 源码安装
1. 前往 [Github 代码托管地址](https://registry.code.tuya-inc.top/shengjun.zhang/tuya_cloud_sdk_java) 下载源码压缩包。
2. 解压源码包到您项目合适的位置。
3. 代码中引用对应模块代码，可参考示例。

## 引用jar包安装
1. 联系相关人员获取jar包。
2. 在您的代码中添加jar包到合适位置

## 使用说明
SDK中提供了两种方式实现接口请求，如果您需要自实现一些接口，您可以任意选择一种。您也可以联系我们，我们会及时更新。

### 一、自定义类实现(推荐)
#### 定义请求类
请求类需要实现com.tuya.api.common.ApiRequest接口，完善其中getRequestMethod和getRequestUrl两个方法。如果请求需要传递body参数，
需要再实现com.tuya.api.common.ApiRequestBody接口，完善getRequestBody方法。
```java
    /**
     * 同步用户请求类
     */
    public class SyncUserReq implements ApiRequest, ApiRequestBody {
    
        /**
         * 渠道标识符
         */
        @NotBlank
        private String schema;
    
        /**
         * 用户
         */
        @NotNull
        @Valid
        private SyncUserVO user;
    
        public SyncUserReq(String schema, SyncUserVO user) {
            this.schema = schema;
            this.user = user;
        }
    
        public HttpMethod getRequestMethod() {
            return HttpMethod.POST;
        }
    
        public String getRequestUrl() {
            return String.format("/v1.0/apps/%s/user", this.schema);
        }
    
        public String getRequestBody() {
            return new Gson().toJson(this.user);
        }
    }
```

#### 定义client类，暴露请求方法。
```java
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
```

#### 调用方法
```java
    // 初始化开发者信息，对应涂鸦云AccessId、AccessKey、涂鸦云服务url
    lientConfig.init(accessId, accessKey, RegionEnum.URL_CN);

    // 同步用户示例
    SyncUserVO vo = new SyncUserVO("86", "17265439876", "17265439876", "1231231", 1);
    UserClient.syncUser(schema, vo);
```

> 调用接口前需要初始化开发者信息

### 二、通用接口
调用CommonClient, 传入相应的参数即可
```java
    // 初始化开发者信息，对应涂鸦云AccessId、AccessKey、涂鸦云服务url
    lientConfig.init(accessId, accessKey, RegionEnum.URL_CN);

    // 通用接口示例
    SyncUserVO vo = new SyncUserVO("86", "17265439876", "17265439876", "1231231", 1);
    TuyaResult result = CommonClient.sendRequest("/v1.0/apps/xxx/user", HttpMethod.POST, null, vo);
    System.out.println(result);
```
> 调用接口前需要初始化开发者信息

## 目前支持的API

|  Method                   | API                                               | 描述  |
|  ----                     | ----                                              | ----  |
| TokenClient.getToken         | GET  /v1.0/token?grant_type=1                     | [简单模式获取access_token](https://docs.tuya.com/docDetail?code=K8uuxenajovgv) |
| TokenClient.refreshToken     | GET  /v1.0/token/{{easy_refresh_token}}           | [刷新token](https://docs.tuya.com/docDetail?code=K8uuxfcvdsqwm) |
|  |  |  |
| DeviceClient.getDevice          | GET  /v1.0/devices/{{device_id}}                  | [获取设备信息](https://docs.tuya.com/docDetail?code=K8uuxen89a81x) |
| DeviceClient.getDeviceFunctions | GET  /v1.0/devices/{deviceId}/functions | [获取设备支持的function列表](https://docs.tuya.com/docDetail?code=K8uuxemwya69p) |
| DeviceClient.getDeviceFunctionByCategory | GET  /v1.0/functions/{category} | [根据category获取function列表](https://docs.tuya.com/docDetail?code=K8uuxemym7qkt) |
| DeviceClient.getDeviceStatus | GET  /v1.0/devices/{{device_id}}/status           | [获取设备功能点的信息](https://docs.tuya.com/docDetail?code=K8uuxen4ux749) |
| DeviceClient.getDeviceList | GET  /v1.0/devices/status?device_ids={{device_id}} | [批量获取设备状态](https://docs.tuya.com/docDetail?code=K8uuxenar6kgc) |
| DeviceClient.postDeviceCommand | POST  /v1.0/devices/{{device_id}}/commands        | [设备指令下发](https://docs.tuya.com/docDetail?code=K8uuxfcxbpwlo) |
| DeviceClient.deleteDevice | DELETE  /v1.0/devices/{device_id} | [移除设备](https://docs.tuya.com/docDetail?code=K8uuxemvwtp3z) |
|  |  |  |
| DeviceClient.generateDeviceToken | POST /v1.0/devices/token       | [生成设备配网token](https://docs.tuya.com/docDetail?code=K8uuxfcujsk6n) |
| DeviceClient.getDevicesByToken | GET  /v1.0/devices/tokens/{{pair_token}}         | [根据token获取设备列表](https://docs.tuya.com/docDetail?code=K8uuxemz174o3) |
| DeviceClient.getDeviceListByUid | GET /v1.0/users/{uid}/devices | [根据用户id获取设备列表](https://docs.tuya.com/docDetail?code=K8uuxfcuesrh7) |
|  |  |  |
| UserClient.syncUser   | POST  /v1.0/apps/{schema}/user | [云端用户注册](https://docs.tuya.com/docDetail?code=K8uuxfcuhc2ei) |
| UserClient.getUsers   | GET  /v1.0/apps/{schema}/users?page_no=&page_size= | [获取用户列表](https://docs.tuya.com/docDetail?code=K8uuxemwe9kwb) |


## 常见问题

### 关于refreshToken接口

注意： refreshToken接口会返回一个新的access_token，即使旧的token还未过期。

这个逻辑在GetToken方法中已经做了，用户一般不需要调用refresh接口。

### 每次调用api之前，是否需要获取token或者刷新token？

不需要，这层逻辑已经在api方法中实现了。token信息会缓存到内存中。

### 调用某个接口时，如果token已经过期，需要手动调用refresh-token接口？

不需要，在GetToken()方法实现中，会检查token是否过期。如果过期会去重新拉取。

### 如果你的token会在多个节点中去刷新，那么你需要自己实现common.TokenLocalManage interface
涂鸦的云token，只保证面向的用户级别刷新不会有问题，但是一个用户的token在多个节点并发刷新，就会导致一个节点是成功的，
其他都是失败；
因为 `获取token`接口会返回一个access_token、refresh_token，但是 `刷新token`接口 会把当前的refresh_token 刷掉，会产生一个新的，旧的失效；

### api方法的异常信息和error需要如何处理？

接口如果返回error，一般可以为url错误或者json解析出错，可联系涂鸦相关人员帮忙修改

如果error为空，但是response的success字段为false，开发者可以根据Msg字段的详细错误信息进行排查

### 获取设备列表接口，如果多个deviceID，需要怎么拼接？

多个deviceID，以英文逗号分割拼接

### 获取用户列表接口中，schema指的是什么？

创建APP-SDK后，详情页面的渠道标识符就是schema

### v1.0/devices/tokens/{{pair_token}}接口，pair_token是指什么？如何获取？

pair_token是指app下的某个用户的配网token，可以从v1.0/devices/token获取。


