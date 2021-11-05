package com.yc.core.aspect;

import com.alibaba.fastjson.JSONObject;
import com.yc.core.model.dto.AOPTestDTO;
import com.yc.core.utils.IdWorker;
import com.yc.core.utils.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 日志的切面
 */
@Aspect
@Component
public class LogAspect {

    @Resource
    private IdWorker idWorker;

    @Pointcut("execution(public * com.yc.core.controller.*.*(..))")
    public void log(){}

    @Pointcut("@annotation(com.yc.core.annotation.Log)")
    public void logAnnotation(){}

    /**
     * 使用环绕通知实现日志打印
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logAnnotation()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获得执行方法的类和名称
        String className = joinPoint.getTarget().getClass().getName();
        //获得方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        //获得参数
        Object[] args = joinPoint.getArgs();
        long requestId = idWorker.nextId();
        //打印参数
        LogHelper.writeInfoLog(className, methodName, "requestId:" + requestId + ",params:" + JSONObject.toJSONString(args));
        long startTime = System.currentTimeMillis();
        //执行业务方法
        Object result = null;
        try {
            Object[] newParam = new Object[args.length + 1];
            for(int i = 0; i < args.length; i++) {
                if (args[i] instanceof AOPTestDTO) {
                    AOPTestDTO tempAopTestDTO = (AOPTestDTO) args[i];
                    tempAopTestDTO.setRequestId(requestId);
                }
            }
            result = joinPoint.proceed(newParam);
        } catch (Exception e) {
            LogHelper.writeErrLog(className, methodName, "requestId:" + requestId + ",异常啦：" + LogAspect.getStackTrace(e));
        }
        long endTime = System.currentTimeMillis();
        //打印结果
        LogHelper.writeInfoLog(className, methodName, "requestId:" + requestId + ",耗时：" + (endTime - startTime) +  "ms，result:" + JSONObject.toJSONString(result));
        //返回
        return result;
    }

    /**
     * 获取异常的堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            pw.close();
        }
    }
}
