package com.itheima.dao;

import com.itheima.bean.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectAll();
}
