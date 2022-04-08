package com.yc.serverconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "yc-server-provider")
public interface ProviderClient {
    @GetMapping(value = "provider/test")
    String test();
}
