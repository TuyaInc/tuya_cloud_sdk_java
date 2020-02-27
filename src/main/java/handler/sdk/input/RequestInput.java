package handler.sdk.input;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 请求入参类
 */
public class RequestInput implements Serializable {
    private String action;
    private JSONObject params;
    private Object userInfo;

    private static final long serialVersionUID = -7079321900865258872L;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }
}
