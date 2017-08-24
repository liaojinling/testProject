package com.api.service;

import com.api.constant.ConfigConstValue;

/**
 * Created by owen on 2017-7-13.
 */
public class RPCServiceFactory {

    public RPCServiceFactory() {
    }

    public static <T> T createDubboRemotService(Class<T> clazz) {
        return (T) DubboClientService.getInstance().getService(clazz, ConfigConstValue.dubboAddress);
    }

    public static <T> T createDubboRemotService(Class<T> clazz, String dubboAddress) {
        return (T) DubboClientService.getInstance().getService(clazz, dubboAddress);
    }

    public static SocketClientService createSocketService(String address, String port) {
        return new SocketClientService(address, port);
    }

    public static <T> T createHessianService(Class<T> clazz, String url) {
        return (T) HessianClientService.handler(clazz, url);
    }

    public static HttpClientService createHttpService() {
        return new HttpClientService();
    }

    public static WebService createWebService(){
        return new WebService();
    }
}
