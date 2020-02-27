package com.tuya.api.client.openiothub.models;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 *
 * 请求mqtt链接配置 result
 */
public class MqttAccessConfigRes implements Serializable {

    //mqtt链接地址: ip+port
    @JSONField(name = "url")
    private String url;

    //过期时间
    @JSONField(name = "expire_time")
    private String expireTime;

    //用户链接mqtt账号
    @JSONField(name = "username")
    private String username;

    //用户链接mqtt密码
    @JSONField(name = "password")
    private String password;

    // mqtt 链接通道 clientId
    @JSONField(name = "client_id")
    private String clientId;

    //mqtt 设备上报 接受topic
    @JSONField(name = "source_topic")
    private String sourceTopic;

    //下发控制指令mqtt topic
    @JSONField(name = "sink_topic")
    private String sinkTopic= "cloud/token/out/{dev_id}";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSourceTopic() {
        return sourceTopic;
    }

    public void setSourceTopic(String sourceTopic) {
        this.sourceTopic = sourceTopic;
    }

    public String getSinkTopic() {
        return sinkTopic;
    }

    public void setSinkTopic(String sinkTopic) {
        this.sinkTopic = sinkTopic;
    }
}

