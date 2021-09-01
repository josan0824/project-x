package com.yc.core.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testswagger")
@Api(tags = "测试Swagger")
public class TestSwaggerController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return "test swagger";
    }

}
