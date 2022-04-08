package com.yc.serverconsumer.controller;

import com.yc.serverconsumer.client.ProviderClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("test")
public class TestDisconverController {

    @Resource
    private ProviderClient providerClient;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return providerClient.test();
    }
}
