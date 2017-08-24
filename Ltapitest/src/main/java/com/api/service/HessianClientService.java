package com.api.service;

import com.caucho.hessian.client.HessianProxyFactory;
import java.net.MalformedURLException;

/**
 * Created by owen on 2017-7-13.
 */
public class HessianClientService {
    private static HessianProxyFactory factory = new HessianProxyFactory();

    public HessianClientService() {
    }

    public static Object handler(Class<?> api, String url) {
        Object obj = null;

        try {
            obj = factory.create(api, url);
        } catch (MalformedURLException var4) {
            var4.printStackTrace();
        }

        return obj;
    }

    static {
        factory.setOverloadEnabled(true);
    }
}
