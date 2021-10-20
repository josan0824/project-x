package com.yc.core.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("AOP返回结果")
public class AOPTestVO {
    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "信息")
    private String msg;
}
