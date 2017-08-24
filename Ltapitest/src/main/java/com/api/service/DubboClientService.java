package com.api.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.api.constant.ConfigConstValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by owen on 2017-7-13.
 */
public class DubboClientService {
    private static Logger logger = LoggerFactory.getLogger(DubboClientService.class);
    public static Map<String, Object> services = new HashMap();
    private static DubboClientService dubboClientService;

    public static DubboClientService getInstance() {
        if(dubboClientService == null) {
            Class var0 = DubboClientService.class;
            synchronized(DubboClientService.class) {
                dubboClientService = new DubboClientService();
            }
        }

        return dubboClientService;
    }

    private DubboClientService() {
    }

    public Object getService(Class clazz, String dubboAdress) {
        String className = clazz.getName();
        if(null == services.get(className + "&" + dubboAdress)) {
            Object rpcService = this.getRpcService(className, dubboAdress);
            services.put(className + "&" + dubboAdress, rpcService);
            return rpcService;
        } else {
            return services.get(className + "&" + dubboAdress);
        }
    }

    private Object getRpcService(String className, String dubboAdress) {
        Class interfaceClass = this.loadClass(className);
        ReferenceConfig reference = new ReferenceConfig();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("Test");
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(dubboAdress);
        reference.setApplication(applicationConfig);
        reference.setInterface(interfaceClass);
        reference.setTimeout(Integer.valueOf(ConfigConstValue.dubbotimeout));
        reference.setRegistry(registry);
        reference.setRetries(Integer.valueOf(0));
        Object service = reference.get();
        return service;
    }

    private Class loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException var3) {
            logger.error(var3.getMessage(), var3);
            throw new RuntimeException(var3.getMessage(), var3);
        }
    }
}
