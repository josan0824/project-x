package com.yc.core.controller;

import com.yc.core.annotation.Log;
import com.yc.core.model.dto.AOPTestDTO;
import com.yc.core.model.vo.AOPTestVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aop")
@Api(tags = "测试AOP")
public class AOPTestController {

    @Log
    @PostMapping(value = "testannotation")
    public AOPTestVO testannotation(@RequestBody AOPTestDTO aopTestDTO) {
        AOPTestVO aopTestVO = new AOPTestVO();
        aopTestVO.setCode(1);
        aopTestVO.setMsg("调用成功");
        return aopTestVO;
    }

    @PostMapping(value = "testlog")
    public AOPTestVO testLog(@RequestBody AOPTestDTO aopTestDTO) {
        AOPTestVO aopTestVO = new AOPTestVO();
        aopTestVO.setCode(1);
        aopTestVO.setMsg("哈哈哈");
        return aopTestVO;
    }

    @PostMapping(value = "testException")
    public AOPTestVO testException() {
        int a = 0;
        int b = 2 / a;
        AOPTestVO aopTestVO = new AOPTestVO();
        aopTestVO.setCode(1);
        aopTestVO.setMsg("哈哈哈");
        return aopTestVO;
    }

    @PostMapping(value = "testSubThreadException")
    public AOPTestVO testSubThreadException() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                int b = 2 / a;
            }
        }).start();
        AOPTestVO aopTestVO = new AOPTestVO();
        aopTestVO.setCode(1);
        aopTestVO.setMsg("哈哈哈");
        return aopTestVO;
    }
}
