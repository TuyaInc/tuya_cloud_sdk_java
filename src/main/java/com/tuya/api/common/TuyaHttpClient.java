package com.tuya.api.common;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * tuya client
 */
public class TuyaHttpClient {

    private OkHttpClient okHttpClient;

    private static class SingletonClientHolder {
        private static final TuyaHttpClient INSTANCE = new TuyaHttpClient();
    }

    private TuyaHttpClient() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TuyaHttpClientCnnfig.connTimeout, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(TuyaHttpClientCnnfig.readTimeout, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(TuyaHttpClientCnnfig.writeTimeout, TimeUnit.SECONDS);
    }

    public static final TuyaHttpClient getInstance(){
        return SingletonClientHolder.INSTANCE;
    }

    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }
}
