package com.yc.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

}
