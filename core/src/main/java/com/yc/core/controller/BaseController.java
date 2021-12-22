package com.yc.core.controller;

import com.yc.core.annotation.Log;
import com.yc.core.model.dto.AOPTestDTO;
import com.yc.core.model.vo.AOPTestVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: josan_tang
 * @create_date: 2021/11/12 13:58
 * @desc:
 * @version:
 */
@RestController
@Api(tags = "测试AOP")
public interface BaseController {
    @PostMapping(value = "testannotation")
    AOPTestVO testannotation(@RequestBody AOPTestDTO aopTestDTO);
}
