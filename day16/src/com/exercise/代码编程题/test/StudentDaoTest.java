package com.exercise.代码编程题.test;

import com.exercise.代码编程题.dao.StudentDao;
import com.exercise.代码编程题.pojo.Student;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class StudentDaoTest {
    Scanner sc = new Scanner(System.in);

    @Test//查询所有
    public void testSelectAll() throws Exception{
        StudentDao dao = new StudentDao();
        List<Student> list = dao.selectAll();
        System.out.println(list);
    }

    @Test//分页查询
    public void testSelectByPage() throws Exception{
        System.out.println("请输入开始页码：");
        int start =sc.nextInt();
        System.out.println("请输入每页大小：");
        int pageSize =sc.nextInt();

        StudentDao dao = new StudentDao();
        List<Student> list = dao.selectByPage(start,pageSize);
        System.out.println(list);
    }

    @Test//根据id查询
    public void testSelectById() throws Exception{
        System.out.println("请输入id：");
        int id =sc.nextInt();

        StudentDao dao = new StudentDao();
        Student student = dao.selectById(id);
        System.out.println(student);
    }

    @Test//插入学生
    public void testInsert() throws Exception{
        System.out.println("请输入id：");
        int id =sc.nextInt();
        System.out.println("请输入NAME：");
        String NAME =sc.next();
        System.out.println("请输入age：");
        int age =sc.nextInt();

        Student student = new Student(id,NAME,age);
        StudentDao dao = new StudentDao();
        int st = dao.insert(student);
        System.out.println("插入数量："+st);
    }

    @Test//更新学生
    public void testUpdate() throws Exception{
        System.out.println("请输入id：");
        int id =sc.nextInt();
        System.out.println("请输入NAME：");
        String NAME =sc.next();
        System.out.println("请输入age：");
        int age =sc.nextInt();

        Student student = new Student(id,NAME,age);
        StudentDao dao = new StudentDao();
        int st = dao.update(student);
        System.out.println("更新数量："+st);
    }

    @Test//通过id删除学生
    public void testDelete() throws Exception{
        System.out.println("请输入id：");
        int id =sc.nextInt();

        StudentDao dao = new StudentDao();
        int st = dao.delete(id);
        System.out.println("删除数量："+st);
    }
}
