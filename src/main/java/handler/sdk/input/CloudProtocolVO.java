package handler.sdk.input;

import java.io.Serializable;

/**
 * mqtt数据协议VO
 * @param <P>
 * @param <T>
 */
public class CloudProtocolVO<P, T>  implements Serializable {

    private P protocol;
    private T data;
    private String pv;
    private long t;
    private String sign;

    public CloudProtocolVO() {
    }

    public P getProtocol() {
        return protocol;
    }

    public void setProtocol(P protocol) {
        this.protocol = protocol;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
