package com.tuya.api.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class ProtocolAESBase64Util {
    private static final String AES = "AES";
    private String ALGO;
    private byte[] keyValue;

    public ProtocolAESBase64Util() {
    }

    public String encrypt(String data) throws Exception {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(1, key);
        byte[] encVal = c.doFinal(StringUtils.getBytesUtf8(data));
        String encryptedValue = Base64.encodeBase64String(encVal);
        return encryptedValue;
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(2, key);
        byte[] decodedValue = Base64.decodeBase64(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = StringUtils.newStringUtf8(decValue);
        return decryptedValue;
    }

    public String encryptURLSafe(String data) throws Exception {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(1, key);
        byte[] encVal = c.doFinal(StringUtils.getBytesUtf8(data));
        String encryptedValue = Base64.encodeBase64URLSafeString(encVal);
        return encryptedValue;
    }

    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(this.keyValue, this.ALGO);
        return key;
    }

    public String getALGO() {
        return this.ALGO;
    }

    public void setALGO(String aLGO) {
        this.ALGO = aLGO;
    }

    public byte[] getKeyValue() {
        return this.keyValue;
    }

    public void setKeyValue(byte[] keyValue) {
        this.keyValue = keyValue;
    }

    public static String decrypt(String data, String secretKey) throws Exception {
        ProtocolAESBase64Util aes = new ProtocolAESBase64Util();
        aes.setALGO("AES");
        aes.setKeyValue(secretKey.getBytes());
        return aes.decrypt(data);
    }

    public static String encrypt(String data, String secretKey) throws Exception {
        ProtocolAESBase64Util aes = new ProtocolAESBase64Util();
        aes.setALGO("AES");
        aes.setKeyValue(secretKey.getBytes());
        return aes.encrypt(data);
    }

    public static String encryptURLSafe(String data, String secretKey) throws Exception {
        ProtocolAESBase64Util aes = new ProtocolAESBase64Util();
        aes.setALGO("AES");
        aes.setKeyValue(secretKey.getBytes());
        return aes.encryptURLSafe(data);
    }

    public static void main(String[] args) throws Exception {
        String data = "{\n" +
                "    \"action\": \"ConvertMqttData\",\n" +
                "    \"params\": {\n" +
                "        \"data\": \"4XXXdhr0xCb4/mTExfYaLbHHizarAkZwRE5yD/iSS6o=\",\n" +
                "        \"type\": \"DEC\"\n" +
                "    }\n" +
                "}";
        String passwordEnc = encrypt(data, "eafc08bbbde32d8d");
//        String passwordEncURLSafe = encryptURLSafe(data, "eafc08bbbde32d8d");
//        String passwordDec = decrypt(passwordEnc, "eafc08bbbde32d8d");
//        String passwordDecURLSafe = decrypt(passwordEnc, "eafc08bbbde32d8d");
        System.out.println("原来的密码 : " + data);
        System.out.println("加密后的密码 : " + passwordEnc);
//        System.out.println("加密后的密码URLSafe : " + passwordEncURLSafe);
//        System.out.println("解密后的原密码 : " + passwordDec);
//        System.out.println("解密后的原密码URLSafe : " + passwordDecURLSafe);
    }

}
