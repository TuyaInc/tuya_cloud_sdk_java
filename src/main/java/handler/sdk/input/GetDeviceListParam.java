package handler.sdk.input;

import java.io.Serializable;

/**
 * 获取设备列表的请求入参
 */
public class GetDeviceListParam implements Serializable {
    private static final long serialVersionUID = 1600617119655354774L;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
