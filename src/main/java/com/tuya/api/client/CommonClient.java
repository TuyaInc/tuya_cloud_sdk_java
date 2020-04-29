package com.tuya.api.client;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Response;
import com.tuya.api.client.token.TokenClient;
import com.tuya.api.common.*;
import com.tuya.api.exception.TuyaCloudSDKException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 通用客户端类
 */
public class CommonClient {


    /**
     * 重试次数
     */
    private static final int maxRetry = 3;


    /**
     * 执行请求
     *
     * @param url 请求url
     * @param method  请求方法
     * @param header 增加的请求头
     * @param body 请求体
     * @return
     */
    public static TuyaResult sendRequest(String url, HttpMethod method, Map<String, String> header, Object body) {
        TuyaResult result = null;
        int retry = CommonClient.maxRetry;
        boolean retryFlag = Boolean.TRUE;

        while (retry >= 0 && retryFlag) {
            try {
                result = execute(url, method, header, body);
                retryFlag = Boolean.FALSE;
            } catch (TuyaCloudSDKException e) {
                // token无效，重新获取token
                if (e.getCode() != null && 1010 == e.getCode() && retry > 0) {
                    retry--;
                    TokenClient.getToken();
                } else {
                    throw e;
                }
            }
        }

        return result;
    }


    /**
     * 执行请求
     *
     * @param url
     * @param method
     * @param header
     * @param body
     * @return
     */
    private static TuyaResult execute(String url, HttpMethod method, Map<String, String> header, Object body) {
        // 验证开发者信息
        if (MapUtils.isEmpty(Constant.map)) {
            throw new TuyaCloudSDKException("未初始化开发者信息！");
        }

        if (StringUtils.isNotBlank(url) && !url.startsWith("http")) {
            url = Constant.map.get(Constant.ENDPOINT) + url;
        }

        Headers headers = RequestHandler.getHeader(true, header);

        String bodyStr = "";
        if (body != null) {
            bodyStr = new Gson().toJson(body);
        }

        Response response;
        if (HttpMethod.GET.equals(method)) {
            response = RequestHandler.getRequest(url, headers);
        } else if (HttpMethod.POST.equals(method)) {
            response = RequestHandler.postRequest(url, bodyStr, headers);
        } else if (HttpMethod.PUT.equals(method)) {
            response = RequestHandler.putRequest(url, bodyStr, headers);
        } else if (HttpMethod.DELETE.equals(method)) {
            response = RequestHandler.deleteRequest(url, bodyStr, headers);
        } else {
            throw new TuyaCloudSDKException("Method only support GET, POST, PUT, DELETE");
        }

        try {
            TuyaResult result = JSONObject.parseObject(response.body().string(), TuyaResult.class);
            if (!result.getSuccess()) {
                throw new TuyaCloudSDKException(result.getCode(), ErrorCode.map.get(result.getCode()));
            }

            return result;
        } catch (IOException e) {
            throw new TuyaCloudSDKException(e.getMessage());
        }
    }
}
