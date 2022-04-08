package com.yc.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author josan_tang
 */
public class Main {

    public static void main(String[] args) {
        //正常操作
/*        Book book = new Book();
        book.setBookName("bookName");
        book.setAuthor("author");
        book.printBookInfo();*/

        //1. 读取配置文件实例化一个IOC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/yc/springframework/spring-config.xml");
        System.out.println("在调用getBean之前");
        //2. 从容器中获取bean
        DITest diTest = applicationContext.getBean("ditest", DITest.class);
    }
}
