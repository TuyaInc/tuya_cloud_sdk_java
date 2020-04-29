package handler.sdk.input;

import java.io.Serializable;
import java.util.List;

/**
 * 控制设备的请求入参
 */
public class ControlParam implements Serializable {
    private static final long serialVersionUID = -7781015681662779583L;
    private String deviceId;
    private List<Command> commands;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
