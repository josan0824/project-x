package com.yc.core.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.yc.core.model.bo.HiberbateTestBO;
import com.yc.core.model.response.LeeJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/val")
@Api(tags = "测试参数校验")
public class ValidateController {

    @PostMapping("/test")
    @ApiOperation(value = "参数校验测试", notes = "测试校验")
    public LeeJSONResult test(@Valid @RequestBody HiberbateTestBO hiberbateTestBO, BindingResult result) {
        if (result.hasErrors()) {
            //如果没有通过，则提示
            Map<String, String> map = getErrors(result);
            return LeeJSONResult.errorMap(map);
        }
        return LeeJSONResult.ok();
    }

    /**
     * 得到具体的错误map
     * @param result
     * @return
     */
    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }
}
