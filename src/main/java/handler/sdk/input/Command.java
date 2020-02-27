package handler.sdk.input;

/**
 * 设备控制指令
 *
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-15 15:42
 */
public class Command {

    private String code;
    private Object value;

    public Command() {
    }

    public Command(String code, Object value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
