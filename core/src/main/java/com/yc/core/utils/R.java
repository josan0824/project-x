package com.yc.core.utils;

import java.util.HashMap;

/**
 * 平台通用的统一返回对象
 *
 * @author ai-srd-bd2
 */
public class R<T> extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String SUCCESS_TAG = "success";
    public static final String DATA_TAG = "data";

    public R() {
    }

    public R(int code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
        if (code == 200) {
            super.put("success", true);
        } else {
            super.put("success", false);
        }

    }

    public R(int code, String msg, Boolean success) {
        super.put("code", code);
        super.put("msg", msg);
        super.put("success", success);

    }

    public R(int code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        if (StringUtils.isNotNull(data)) {
            super.put("data", data);
        }

        if (code == 200) {
            super.put("success", true);
        } else {
            super.put("success", false);
        }

    }

    public static R success() {
        return success("操作成功");
    }

    public static R success(String msg) {
        return success(msg, (Object) null);
    }

    public static R success(String msg, Object data) {
        return new R(200, msg, data);
    }

    public static <T> R<T> success(T data) {
        return success("操作成功", data);
    }

    public static R error() {
        return error("操作失败");
    }

    public static R error(String msg) {
        return error(msg, (Object) null);
    }

    public static R error(String msg, Object data) {
        return new R(500, msg, data);
    }

    public static R error(int code, String msg) {
        return new R(code, msg, (Object) null);
    }
}
