package com.tuya.api.common;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import com.tuya.api.client.token.TokenClient;
import com.tuya.api.exception.TuyaCloudSDKException;
import com.tuya.api.utils.SignUtil;
import com.tuya.api.utils.ValidationUtil;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 请求处理类
 */
public class RequestHandler {

    private static final MediaType CONTENT_TYPE = MediaType.parse("application/json");

    /**
     * 读超时时间（秒）
     */
    private static final int readTimeout = 30;

    /**
     * 写超时时间（秒）
     */
    private static final int writeTimeout = 30;

    /**
     * 连接超时时间（秒）
     */
    private static final int connTimeout = 30;

    /**
     * 重试次数
     */
    private static final int maxRetry = 3;


    /**
     * 执行请求, 默认需要携带token
     *
     * @param ar
     * @return
     * @throws Exception
     */
    public static TuyaResult sendRequest(ApiRequest ar) {
        return sendRequest(ar, Boolean.TRUE);
    }


    /**
     * 执行请求, token过期自动重试
     *
     * @param ar
     * @param withToken
     * @return
     */
    public static TuyaResult sendRequest(ApiRequest ar, Boolean withToken) {
        TuyaResult result = null;
        int retry = RequestHandler.maxRetry;
        boolean retryFlag = Boolean.TRUE;

        while (retry >= 0 && retryFlag) {
            try {
                result = execute(ar, withToken);
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
     * @param ar 请求实体
     * @param withToken 是否需要token
     * @return
     */
    private static TuyaResult execute(ApiRequest ar, Boolean withToken) {
        // 验证开发者信息
        if (MapUtils.isEmpty(Constant.map)) {
            throw new TuyaCloudSDKException("未初始化开发者信息！");
        }

        // 验证请求参数
        ValidationUtil.validateBean(ar);

        HttpMethod method = ar.getRequestMethod();

        String url = Constant.map.get(Constant.ENDPOINT) + ar.getRequestUrl();

        Headers headers = getHeader(withToken, null);

        String body = "";
        if (ar instanceof ApiRequestBody) {
            body = ((ApiRequestBody) ar).getRequestBody();
        }

        Response response;
        if (HttpMethod.GET.equals(method)) {
            response = getRequest(url, headers);
        } else if (HttpMethod.POST.equals(method)) {
            response = postRequest(url, body, headers);
        } else if (HttpMethod.PUT.equals(method)) {
            response = putRequest(url, body, headers);
        } else if (HttpMethod.DELETE.equals(method)) {
            response = deleteRequest(url, body, headers);
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
            throw new TuyaCloudSDKException(500, e.getMessage());
        }
    }

    /**
     * 生成header
     *
     * @param withToken 是否需要携带token
     * @param headerMap 自定义header
     * @return
     */
    public static Headers getHeader(Boolean withToken, Map<String, String> headerMap) {
        long t = System.currentTimeMillis();
        Headers.Builder hb = new Headers.Builder();
        hb.add("client_id", Constant.map.get(Constant.ACCESS_ID))
                .add("t", String.valueOf(t))
                .add("sign_method", "HMAC-SHA256");

        // 自定义header
        if (MapUtils.isNotEmpty(headerMap)) {
            Iterator<Map.Entry<String, String>> entries = headerMap.entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry<String, String> entry = entries.next();
                hb.add(entry.getKey(), entry.getValue());
            }
        }

        if (withToken) {
            String accessToken = TokenCache.getToken();
            hb.add("sign", calcSign(Constant.map.get(Constant.ACCESS_ID), Constant.map.get(Constant.ACCESS_KEY),
                    t, accessToken, true))
            .add("access_token", accessToken);
        } else {
            hb.add("sign", calcSign(Constant.map.get(Constant.ACCESS_ID), Constant.map.get(Constant.ACCESS_KEY),
                    t, null, false));
        }
        return hb.build();
    }

    /**
     * 计算sign
     *
     * @param accessId
     * @param secret
     * @param t
     * @param accessToken
     * @param withToken
     * @return
     */
    private static String calcSign(String accessId, String secret, long t, String accessToken, Boolean withToken) {
        String message = accessId + t;
        if (withToken) {
            message = accessId + accessToken + t;
        }
        return SignUtil.encrytSHA256(message, secret);
    }

    /**
     * 处理get请求
     *
     * @param url
     * @param headers
     * @return
     */
    public static Response getRequest(String url, Headers headers) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .get()
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TuyaCloudSDKException(500, e.getClass().getName() + "-" + e.getMessage());
        }

        return doRequest(request);
    }

    /**
     * 处理post请求
     *
     * @param url
     * @param body
     * @param headers
     * @return
     */
    public static Response postRequest(String url, String body, Headers headers) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(CONTENT_TYPE, body))
                    .headers(headers)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TuyaCloudSDKException(500, e.getClass().getName() + "-" + e.getMessage());
        }

        return doRequest(request);
    }

    /**
     * 处理put请求
     *
     * @param url
     * @param body
     * @param headers
     * @return
     */
    public static Response putRequest(String url, String body, Headers headers) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .put(RequestBody.create(CONTENT_TYPE, body))
                    .headers(headers)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TuyaCloudSDKException(500, e.getClass().getName() + "-" + e.getMessage());
        }

        return doRequest(request);
    }


    /**
     * 处理delete请求
     *
     * @param url
     * @param body
     * @param headers
     * @return
     */
    public static Response deleteRequest(String url, String body, Headers headers) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .delete(RequestBody.create(CONTENT_TYPE, body))
                    .headers(headers)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TuyaCloudSDKException(500, e.getClass().getName() + "-" + e.getMessage());
        }

        return doRequest(request);
    }

    /**
     * 执行请求
     *
     * @param request
     * @return
     */
    private static Response doRequest(Request request) {
        Response response;
        try {
            response = getHttpClient().newCall(request).execute();
        } catch (IOException e) {
            throw new TuyaCloudSDKException(500, e.getClass().getName() + "-" + e.getMessage());
        }
        return response;
    }

    /**
     * 获取 http client
     *
     * @return
     */
    private static OkHttpClient getHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(connTimeout, TimeUnit.SECONDS);
        client.setReadTimeout(readTimeout, TimeUnit.SECONDS);
        client.setWriteTimeout(writeTimeout, TimeUnit.SECONDS);

        return client;
    }
}
