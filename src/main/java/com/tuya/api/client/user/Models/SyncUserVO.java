package com.tuya.api.client.user.Models;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 同步用户VO
 */
public class SyncUserVO {

    /**
     * 国家码
     */
    @NotBlank
    @JSONField(name = "country_code")
    private String countryCode;

    /**
     * 用户名
     */
    @NotBlank
    @JSONField(name = "username")
    private String username;

    /**
     * 用户昵称
     */
    @JSONField(name = "nick_name")
    private String nickName;

    /**
     * 密码
     */
    @NotBlank
    @JSONField(name = "password")
    private String password;

    /**
     * 用户类型：1、手机号；2、邮箱；3、其他类型
     * 默认为其他类型
     */
    @Max(3)
    @Min(1)
    @JSONField(name = "username_type")
    private int usernameType = 3;

    public SyncUserVO() {
    }

    public SyncUserVO(@NotBlank String countryCode, @NotBlank String username, String nickName,
                      @NotBlank String password, @Max(3) @Min(1) int usernameType) {
        this.countryCode = countryCode;
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.usernameType = usernameType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsernameType() {
        return usernameType;
    }

    public void setUsernameType(int usernameType) {
        this.usernameType = usernameType;
    }
}