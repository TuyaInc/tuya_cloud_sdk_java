package com.tuya.api.utils;

import com.tuya.api.exception.TuyaCloudSDKException;
import handler.sdk.input.CloudProtocolVO;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

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

    /**
     * 获取云端签名
     *
     * @param protocolVO
     * @param localKey
     * @return
     */
    public static String getCloudSign(CloudProtocolVO protocolVO, String localKey) {
        return ProtocolMD5Util.getMD5(createCloudSignInput_2_1(protocolVO, localKey));
    }


    private static String createCloudSignInput_2_1(CloudProtocolVO protocolVO, String localKey) {
        TreeMap<String, String> params = new TreeMap();
        params.put("protocol", protocolVO.getProtocol().toString());
        params.put("t", String.valueOf(protocolVO.getT()));
        params.put("pv", protocolVO.getPv());
        if (protocolVO.getData() != null) {
            params.put("data", protocolVO.getData().toString());
        }

        StringBuilder str = new StringBuilder();
        Set<String> keySet = params.keySet();
        Iterator iter = keySet.iterator();

        while(iter.hasNext()) {
            String key = (String)iter.next();
            if (!StringUtils.isBlank((String)params.get(key))) {
                str.append(key);
                str.append("=");
                str.append((String)params.get(key));
                str.append("||");
            }
        }

        str.append(localKey);
        return str.toString();
    }
}
