package com.yc.redis.model.enums;

/**
 * @author: josan_tang
 * @create_date: 2021/12/29 13:18
 * @desc:
 * @version:
 */
public enum DemoEnum {
    SUCCESS(10, "成功"),
    FAIL(20, "失败"),
    RECORDING(30, "录制中");

    public Integer status;
    public String value;

    DemoEnum(Integer status, String value) {
        this.status = status;
        this.value = value;
    }
}
