package com.yc.mybatisdemo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: josan
 * @createDate: 2021-05-10
 */
@Data
public class BasePageDTO implements Serializable {

    private static final long serialVersionUID = 2932613555759049302L;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "数量")
    private Integer limit = 10;
}
