package com.yc.core.model.bo;

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
public class HiberbateTestBO {

    @NotBlank(message = "用户昵称不能为空")
    @ApiModelProperty(value = "用户昵称", name = "nickname", example = "josan")
    private String nickName;
    @Max(value = 20, message = "用户年龄不能超过20")
    private Integer age;

    @NotBlank(message = "用户名称不能用空")
    @Length(max = 6, min = 3, message = "用户长度需要为3-6位")
    private String username;

    @NotBlank(message = "{pwd.not.null}")
    @Pattern(regexp = "/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/", message = "密码必须是6~10位数字和字母的组合 ")
    private String password;

    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "手机号格式不正确")
    private String phone;

    @Email(message = "邮件格式错误")
    private String email;


}
