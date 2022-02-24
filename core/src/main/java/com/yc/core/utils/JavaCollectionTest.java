package com.yc.core.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: josan_tang
 * @create_date: 2022/2/14 15:47
 * @desc: Java集合测试
 * @version:
 */
public class JavaCollectionTest {
    static class Student {
        int age;

        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * List求和
     */
    public static void sum() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setAge(12);
        Student s2 = new Student();
        s2.setAge(24);
        list.add(s1);
        list.add(s2);
        int sum = list.stream().mapToInt(Student::getAge).sum();
        System.out.println("sum:" + sum);
    }

    /**
     * min
     */
    public static void min() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setAge(12);
        Student s2 = new Student();
        s2.setAge(24);
        list.add(s1);
        list.add(s2);
        AtomicInteger min = new AtomicInteger();
        list.stream()
                .min(Comparator.comparing(u -> u.getAge()))
                .ifPresent(e -> {
                    min.set(e.getAge());
                });
        System.out.println("Min: " + min);

    }


    public static void main(String[] args) {
        min();
    }

}
