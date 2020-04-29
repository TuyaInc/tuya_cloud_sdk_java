package com.tuya.api.exception;

/**
 * 异常类
 */
public class TuyaCloudSDKException extends RuntimeException {

    private Integer code;

    public TuyaCloudSDKException(String message) {
        super(message);
    }

    public TuyaCloudSDKException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        if (code != null) {
            return "TuyaCloudSDKException: " +
                    "[" + code + "] " + getMessage();
        }

        return "TuyaCloudSDKException: " + getMessage();
    }
}
