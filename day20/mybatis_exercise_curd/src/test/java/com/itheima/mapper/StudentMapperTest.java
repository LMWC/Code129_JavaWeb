package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentMapperTest {
    SqlSession sqlSession =null;
    StudentMapper studentMapper = null;
    //@Before所在的方法会在每个单元测试方法执行之前执行一次
    @Before
    public void before() throws IOException {
        //1.加载配置文件
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //2.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //3.获取SqlSession对象
        sqlSession = sqlSessionFactory.openSession();

        //4.获取dao接口代理对象
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    //@After注解所在方法 会在执行每个单元测试方法后执行一次
    @After
    public void after(){
        //6.关闭SqlSession对象
        sqlSession.close();
    }

    @Test
    public void testSelectAll() throws IOException {
        //5.调用方法
        List<Student> list = studentMapper.selectAll();

        for (Student stu : list) {
            System.out.println("student = " + stu);
        }
    }

    @Test
    public void testSelectById() throws IOException {
        System.out.println("请输入查询ID:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        //5.调用方法
        Student stu = studentMapper.selectById(id);
        System.out.println("student = " + stu);
    }

    @Test
    public void testSelectByContionPage() throws IOException {
        System.out.println("请输入name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("请输入age:");
        int age = sc.nextInt();
        System.out.println("请输入start:");
        int start = sc.nextInt();
        System.out.println("请输入pagesize:");
        int ps = sc.nextInt();

        List<Student> list = studentMapper.selectByContionPage(name,age,start,ps);
        for (Student stu : list) {
            System.out.println("student = " + stu);
        }
    }

    @Test
    public void testInsert(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入id:");
        int id = sc.nextInt();
        System.out.println("请输入name:");
        String name = sc.next();
        System.out.println("请输入age:");
        int age = sc.nextInt();
        Student stu = new Student(id,name,age);
        int rows = studentMapper.insert(stu);
        sqlSession.commit();
    }

    @Test
    public void testUpdate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入id:");
        int id = sc.nextInt();
        System.out.println("请输入name:");
        String name = sc.next();
        System.out.println("请输入age:");
        int age = sc.nextInt();
        Student stu = new Student(id,name,age);

        int rows = studentMapper.update(stu);

        sqlSession.commit();
    }

    @Test
    public void testDelete(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入删除id:");
        int id = sc.nextInt();
        //调用方法
        int rows = studentMapper.delete(id);

        //增删改操作之后  需要手动提交事务
        sqlSession.commit();
    }

    @Test
    public void testDeleteByIds(){
        int[] ids = new int[100];
        int i =0;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请输入删除id(没有请输入0):");
            int id = sc.nextInt();
            if (id==0||i>=100)
                break;
            ids[i]=id;
            i++;
        }
        int rows = studentMapper.deleteByIds(ids);

        sqlSession.commit();
    }
}
