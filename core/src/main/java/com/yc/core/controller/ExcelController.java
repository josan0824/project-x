package com.yc.core.controller;

import com.yc.core.annotation.Log;
import com.yc.core.model.dto.AOPTestDTO;
import com.yc.core.model.vo.AOPTestVO;
import com.yc.core.utils.ExcelUtils;
import com.yc.core.utils.R;
import io.swagger.annotations.Api;
import org.springframework.aop.framework.AopContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
@Api(tags = "Excel")
public class ExcelController {



    @Log
    @PostMapping(value = "get")
    public R get() {
        List<TradePropertyExcelVO> exportList = new ArrayList<>();
        exportList.add(new TradePropertyExcelVO());
        ExcelUtils<TradePropertyExcelVO> excelUtil = new ExcelUtils<>(TradePropertyExcelVO.class);
        return excelUtil.exportExcel(exportList, "交易项目导入模板");
    }

}
