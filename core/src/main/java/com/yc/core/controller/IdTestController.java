package com.yc.core.controller;

import com.yc.core.utils.SnowflakeIdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 分布式id
 */
@RestController
@Api(tags = "分布式id")
@RequestMapping("/id")
public class IdTestController {

    @GetMapping("/getUUID")
    @ApiOperation(value="得到UUID", notes ="得到UUID")
    public String getUUID() {
        return UUID.randomUUID().toString();
    }

    @GetMapping("/getSnowflakeId")
    @ApiOperation(value="得到雪花算法id", notes ="得到雪花算法id")
    public long getSnowflakeId() {
        return new SnowflakeIdWorker(24).nextId();
    }



}
