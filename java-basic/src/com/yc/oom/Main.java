package com.yc.oom;

import java.lang.management.ManagementFactory;

/**
 * 1.hreap dump生成：
 * windows: jmap -dump:live,format=b,file=heap.hprof <pid>
 * linux： ./jmap -dump:live,format=b,file=heap.hprof <pid>
 * 2. MAT下载地址：https://www.eclipse.org/mat/downloads.php
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 在windows上，获取到得name格式为 1234@userName
        // 1234为PID，@为分隔符，userName为当前用户
        while (true) {
            String pid = ManagementFactory.getRuntimeMXBean().getName();
            int indexOf = pid.indexOf('@');
            if (indexOf > 0)
            {
                pid = pid.substring(0, indexOf);
            }
            System.out.println("当前JVM Process ID: " + pid);
            Thread.sleep(3000);
        }

    }
}
