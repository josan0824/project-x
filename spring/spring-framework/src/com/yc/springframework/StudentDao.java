package com.yc.springframework;

import org.springframework.stereotype.Controller;

@Controller
public interface StudentDao {
    Student getStudent();
}
