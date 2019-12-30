package com.tuya.api.utils;

import com.tuya.api.exception.TuyaCloudSDKException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * sign工具类
 */
public class SignUtil {

    /**
     * HmacSHA256加密
     *
     * @param message
     * @param secret
     * @return
     */
    public static String encrytSHA256(String message, String secret) {
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
            sha256HMAC.init(secretKey);
            byte[] digest = sha256HMAC.doFinal(message.getBytes("UTF-8"));
            return new HexBinaryAdapter().marshal(digest).toUpperCase();
        } catch (Exception e){
            throw new TuyaCloudSDKException(500, "HMAC_SHA256 failed");
        }
    }
}
