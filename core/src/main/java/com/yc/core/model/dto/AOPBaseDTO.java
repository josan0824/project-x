package com.yc.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("aop参数基类")
public class AOPBaseDTO {
    @ApiModelProperty(value = "请求id", hidden = true)
    private long requestId;
}
