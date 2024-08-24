package com.yc.mybatisdemo.model;

import lombok.Data;

import java.util.Date;

/**
 *@author: josan_tang
 *@create_date: 2022/4/8 14:34
 *@desc:
 *@version:
 */
    
/**
    * 商家表
    */
@Data
public class MyAccount {

    /**
     * 主键
     */
    private int id;

    /**
    * 用户表id
    */
    private int urid;

    /**
    * 商家名称
    */
    private String merchantName;

    /**
    * 账号（一般是手机号）
    */
    private String account;

    /**
    * 密码
    */
    private String password;

    /**
     * 公司
     */
    private String company;

    /**
    * 创建日期
    */
    private Date createdTime;

    /**
    * 更新时间
    */
    private Date updatedTime;

    /**
    * 删除状态（0-已删除；1-正常）
    */
    private Integer delFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}