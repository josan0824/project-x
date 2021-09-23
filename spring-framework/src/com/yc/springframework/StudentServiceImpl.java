package com.yc.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    Student student;

    @Override
    public Student getStudent() {
        return studentDao.getStudent();
    }


}
