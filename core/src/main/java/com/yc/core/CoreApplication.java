package com.yc.core;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.yc.core.utils.LogHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Properties;

@SpringBootApplication(scanBasePackages = "com.yc.core")
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class CoreApplication {

    private static final String CLASS_NAME = "CoreApplication";
    public static void main(String[] args) throws NacosException {
        LogHelper.writeInfoLog(CLASS_NAME, "main", "开始启动");
        SpringApplication.run(CoreApplication.class, args);
        LogHelper.writeInfoLog(CLASS_NAME, "main", "启动成功");

        //获取配置
        String serverAddr = "127.0.0.1:8848";
        String dataId = "hydy-web.yml";
        String groupId = "DEFAULT_GROUP";
        Properties persistentProperties = new Properties();
        persistentProperties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(persistentProperties);
        String config = configService.getConfig(dataId, groupId, 5000);
        LogHelper.writeInfoLog(CLASS_NAME, "main", "config:" + config);

    }

}
