package com.yc.springframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCAnnotatedTest {

    public static void main(String[] args) {
        //初始化IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = applicationContext.getBean(StudentService.class);
        Student student = studentService.getStudent();
        System.out.println("学生id:" + student.getId());
        System.out.println("学生姓名：" + student.getName());
    }
}
