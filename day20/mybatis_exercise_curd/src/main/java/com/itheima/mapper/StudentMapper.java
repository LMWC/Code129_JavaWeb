package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    List<Student> selectAll();
    Student selectById(int id);
    //注意多参数的处理，分页查询
    List<Student> selectByContionPage(String name,int age,int start,int pagesize);

    int insert(Student student);

    //注意没有指定的数据不需要更新
    int update(Student student);

    int delete(int id);
    int deleteByIds(@Param("ids") int[] ids);

}
