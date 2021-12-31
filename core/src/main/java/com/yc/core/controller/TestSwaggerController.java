package com.yc.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testswagger")
@Api(tags = "类名称")
public class TestSwaggerController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ApiOperation(value="接口名称", notes ="接口描述")
    public String test() {
        return "test swagger";
    }

    @RequestMapping(value = "testApiParams", method = RequestMethod.GET)
    @ApiOperation(value="测试传参", notes ="接口描述")
    public String testApiParams(@ApiParam(name="参数名称", value="参数描述")
                                        String id,
                                @ApiParam(name="opt", value="opt操作类型 0-取消收藏；1-收藏")
                                        int opt) {
        return "testApiParams";
    }

}
