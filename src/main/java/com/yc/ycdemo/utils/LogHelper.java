package com.yc.ycdemo.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {
    private final static Logger logger = LoggerFactory.getLogger(LogHelper.class);

    public static void writeInfoLog(String pkg, String method, Object... message) {
        logger.info("[package] [" + pkg + "] [method] [" + method + "] [params] " + JSON.toJSONString(message) + "");
    }

    public static void writeErrLog(String pkg, String method, Object... message) {
        logger.error("[package] [" + pkg + "] [method] [" + method + "] [params] " + JSON.toJSONString(message) + "");
    }
}
