package com.yc.springframework;

import org.springframework.stereotype.Repository;

@Repository
public class StudentZDaoImpl implements StudentDao {

    @Override
    public Student getStudent() {
        Student student = new Student();
        student.setId(24);
        student.setName("zhangsan");
        return student;
    }
}
