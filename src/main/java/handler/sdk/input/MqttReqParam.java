package handler.sdk.input;

import java.io.Serializable;

/**
 * mqtt加解密的请求入参
 */
public class MqttReqParam implements Serializable {

    private String type;

    private CloudProtocolVO data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CloudProtocolVO getData() {
        return data;
    }

    public void setData(CloudProtocolVO data) {
        this.data = data;
    }
}
