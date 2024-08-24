package com.yc.mybatisdemo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 测试校验BO
 */

/*** Bean Validation 中内置的 constraint
 *  @Null 被注释的元素必须为 null
 *  @NotNull 被注释的元素必须不为 null
 *  @AssertTrue 被注释的元素必须为 true
 *  @AssertFalse 被注释的元素必须为 false
 *  @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *  @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *  @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *  @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *  @Size(max=, min=) 被注释的元素的大小必须在指定的范围内
 *  @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
 *  @Past 被注释的元素必须是一个过去的日期
 *  @Future 被注释的元素必须是一个将来的日期
 *  @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 *  Hibernate Validator 附加的 constraint
 *  @NotBlank(message =) 验证字符串非null，且长度必须大于0
 *  @Email 被注释的元素必须是电子邮箱地址
 *  @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 *  @NotEmpty 被注释的字符串的必须非空
 *  @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 */
@Data
public class ValidatedTestBO {

    @NotBlank(message = "用户昵称不能为空")
    @ApiModelProperty(value = "用户昵称", name = "nickname", example = "josan")
    private String nickName;

    @Max(value = 20, message = "用户年龄不能超过20")
    private Integer age;

    @NotBlank(message = "用户名称不能用空")
    @Length(max = 6, min = 3, message = "用户长度需要为3-6位")
    private String username;

    @Email(message = "邮件格式错误")
    private String email;

    @ApiModelProperty(value = "地址", example = "南京东路")
    @NotBlank(message = "地址不能为空")
    private String address;

    @ApiModelProperty(value = "手机号", example = "13888888888")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式错误")
    private String phone;

}
