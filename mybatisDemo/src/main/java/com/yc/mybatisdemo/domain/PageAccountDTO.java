package com.yc.mybatisdemo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author: josan
 * @createDate: 2021-05-10
 */
@Data
@ApiModel("用户分页查询")
public class PageAccountDTO extends BasePageDTO {

    private static final long serialVersionUID = 2932613555759049302L;

    @ApiModelProperty(value="用户id", example = "1")
    private String id;
}
