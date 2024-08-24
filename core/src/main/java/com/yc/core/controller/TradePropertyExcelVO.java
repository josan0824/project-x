package com.yc.core.controller;

import com.yc.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**************************************************
 * copyright (c) 2023
 * created by josan.tang
 * date:       2023/12/05
 * description:
 **************************************************/

@Data
@ApiModel(value = "投资交易项目管理_导入实体")
@ToString
public class TradePropertyExcelVO implements Serializable {

    private static final long serialVersionUID = 6104086007948015373L;

    @Excel(name = "项目ID", comments = "必填 1-64个字符", width = 25)
    private String busId;

    @Excel(name = "项目名", comments = "必填 1-30个字符，城市和项目名称唯一", width = 30, commentsWidth = 50)
    private String propertyNameCn;

    @Excel(name = "项目英文名", comments = "1-60个字符", width = 20)
    private String propertyNameEn;

    @Excel(name = "一级分类", comments = "必填(多个用逗号分割) 商业,工业,住宅,公寓,车库,其他", width = 30, commentsWidth = 80)
    private String firstLevelCategory;

    @Excel(name = "二级分类", comments = "必填 \n商业(办公/商业/酒店/综合体)" +
            "\n工业(研发办公-产业园区/研发办公-产业楼宇/仓储物流/厂房)" +
            "\n住宅(住宅)" +
            "\n公寓(长租公寓/服务公寓/养老公寓/保障性住房)" +
            "\n车库(车库/车位)" +
            "\n其他(其他)", width = 30, commentsHeight = 90, commentsWidth = 120)
    private String secondLevelCategory;

    @Excel(name = "建成年份", comments = "4位整数 大于等于1900", width = 10)
    private Integer completionYear;

    @Excel(name = "翻新年份", comments = "4位整数 大于等于1900", width = 10)
    private Integer renovationYear;

    @Excel(name = "占地面积（万㎡）", comments = "1到999999 可输入保留15小数", width = 30)
    private BigDecimal area;

    @Excel(name = "总建筑面积（万㎡）", comments = "1到999999 可输入保留15小数", width = 30)
    private BigDecimal totalArea;

    @Excel(name = "城市", comments = "必填 单个城市", width = 15)
    private String city;

    @Excel(name = "行政区", comments = "多个用逗号分割，城市为多地时不可录入", width = 35)
    private String district;

    @Excel(name = "子市场", comments = "多个用逗号分割，城市为多地时不可录入", width = 35)
    private String submarketName;

    @Excel(name = "经度", comments = "-180到180之间，支持15位小数点", width = 30)
    private BigDecimal longitude;

    @Excel(name = "纬度", comments = "-90到90之间，支持15位小数点", width = 30)
    private BigDecimal latitude;

    @Excel(name = "项目地址",comments = "1-50个字符", width = 30)
    private String address;
}

