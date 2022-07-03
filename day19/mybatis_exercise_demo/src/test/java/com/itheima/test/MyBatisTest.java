package com.itheima.test;

import com.itheima.bean.Company;
import com.itheima.bean.Dept;
import com.itheima.mapper.CompanyMapper;
import com.itheima.mapper.DeptMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MyBatisTest {
    @Test
    public void testCompanyFindById() throws IOException {
        //1.加载MyBatis核心配置文件  myabtis-config.xml：这个配置文件名称可以随便起 但是要放在src/main/resources目录下
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //创建者模式 ：传递is资源 由工厂创建者创建出工厂对象
        //2.通过SqlSessionFactoryBuilder类加载MyBatis配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //整体创建对象流程：SqlSession工厂建造者--build()-->SqlSession工厂--openSession()-->SqlSession对象
        //SqlSessionFactory：相当于JDBC的连接池对象  SqlSession：相当于JDBC的连接对象
        //工厂模式：由工厂创建对应的对象
        //3.由SqlSessionFactory对象得到SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //JDK动态代理
        //4.通过SqlSession对象得到Dao接口的代理对象
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查询id：");
        String id = sc.nextLine();

        //5.使用dao接口代理对象调用方法 完成操作
        Company company = companyMapper.findById(id);

        System.out.println("company = " + company);


        //6.关闭SqlSession对象
        sqlSession.close();

    }

    @Test
    public void testCompanyFindAll() throws IOException {
        //1.加载MyBatis核心配置文件  myabtis-config.xml：这个配置文件名称可以随便起 但是要放在src/main/resources目录下
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //创建者模式 ：传递is资源 由工厂创建者创建出工厂对象
        //2.通过SqlSessionFactoryBuilder类加载MyBatis配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //整体创建对象流程：SqlSession工厂建造者--build()-->SqlSession工厂--openSession()-->SqlSession对象
        //SqlSessionFactory：相当于JDBC的连接池对象  SqlSession：相当于JDBC的连接对象
        //工厂模式：由工厂创建对应的对象
        //3.由SqlSessionFactory对象得到SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //JDK动态代理
        //4.通过SqlSession对象得到Dao接口的代理对象
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);

        //5.使用dao接口代理对象调用方法 完成操作
        List<Company> list = companyMapper.findAll();

        for (Company company : list) {
            System.out.println("company = " + company);
        }

        //6.关闭SqlSession对象
        sqlSession.close();

    }

    @Test
    public void testCompanyFindByCondition() throws IOException {
        //1.加载MyBatis核心配置文件  myabtis-config.xml：这个配置文件名称可以随便起 但是要放在src/main/resources目录下
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //创建者模式 ：传递is资源 由工厂创建者创建出工厂对象
        //2.通过SqlSessionFactoryBuilder类加载MyBatis配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //整体创建对象流程：SqlSession工厂建造者--build()-->SqlSession工厂--openSession()-->SqlSession对象
        //SqlSessionFactory：相当于JDBC的连接池对象  SqlSession：相当于JDBC的连接对象
        //工厂模式：由工厂创建对应的对象
        //3.由SqlSessionFactory对象得到SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //JDK动态代理
        //4.通过SqlSession对象得到Dao接口的代理对象
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入id：");
        String id = sc.nextLine();
        System.out.println("请输入name：");
        String name = sc.nextLine();
        System.out.println("请输入adrress：");
        String adrress = sc.nextLine();
        System.out.println("请输入phone：");
        String phone = sc.nextLine();
        System.out.println("请输入industry：");
        String industry = sc.nextLine();

        Company company = new Company(id,name,adrress,phone,industry);

        //5.使用dao接口代理对象调用方法 完成操作
        List<Company> list = companyMapper.findByCondition(company);

        for (Company co : list) {
            System.out.println("company = " + co);
        }

        //6.关闭SqlSession对象
        sqlSession.close();

    }

    @Test
    public void testDeptFindById() throws IOException {
        //1.加载MyBatis核心配置文件  myabtis-config.xml：这个配置文件名称可以随便起 但是要放在src/main/resources目录下
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //创建者模式 ：传递is资源 由工厂创建者创建出工厂对象
        //2.通过SqlSessionFactoryBuilder类加载MyBatis配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //整体创建对象流程：SqlSession工厂建造者--build()-->SqlSession工厂--openSession()-->SqlSession对象
        //SqlSessionFactory：相当于JDBC的连接池对象  SqlSession：相当于JDBC的连接对象
        //工厂模式：由工厂创建对应的对象
        //3.由SqlSessionFactory对象得到SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //JDK动态代理
        //4.通过SqlSession对象得到Dao接口的代理对象
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查询id：");
        String id = sc.nextLine();

        //5.使用dao接口代理对象调用方法 完成操作
        Dept dept = deptMapper.findById(id);

        System.out.println("Dept = " + dept);


        //6.关闭SqlSession对象
        sqlSession.close();

    }

    @Test
    public void testDeptFindAll() throws IOException {
        //1.加载MyBatis核心配置文件  myabtis-config.xml：这个配置文件名称可以随便起 但是要放在src/main/resources目录下
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //创建者模式 ：传递is资源 由工厂创建者创建出工厂对象
        //2.通过SqlSessionFactoryBuilder类加载MyBatis配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //整体创建对象流程：SqlSession工厂建造者--build()-->SqlSession工厂--openSession()-->SqlSession对象
        //SqlSessionFactory：相当于JDBC的连接池对象  SqlSession：相当于JDBC的连接对象
        //工厂模式：由工厂创建对应的对象
        //3.由SqlSessionFactory对象得到SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //JDK动态代理
        //4.通过SqlSession对象得到Dao接口的代理对象
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);

        //5.使用dao接口代理对象调用方法 完成操作
        List<Dept> list = deptMapper.findAll();

        for (Dept dept : list) {
            System.out.println("Dept = " + dept);
        }

        //6.关闭SqlSession对象
        sqlSession.close();

    }

    @Test
    public void testDeptFindByCondition() throws IOException {
        //1.加载MyBatis核心配置文件  myabtis-config.xml：这个配置文件名称可以随便起 但是要放在src/main/resources目录下
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //创建者模式 ：传递is资源 由工厂创建者创建出工厂对象
        //2.通过SqlSessionFactoryBuilder类加载MyBatis配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //整体创建对象流程：SqlSession工厂建造者--build()-->SqlSession工厂--openSession()-->SqlSession对象
        //SqlSessionFactory：相当于JDBC的连接池对象  SqlSession：相当于JDBC的连接对象
        //工厂模式：由工厂创建对应的对象
        //3.由SqlSessionFactory对象得到SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //JDK动态代理
        //4.通过SqlSession对象得到Dao接口的代理对象
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入id：");
        String id = sc.nextLine();
        System.out.println("请输入name：");
        String name = sc.nextLine();

        Dept dept = new Dept(id,name);

        //5.使用dao接口代理对象调用方法 完成操作
        List<Dept> list = deptMapper.findByCondition(dept);

        for (Dept dp : list) {
            System.out.println("Dept = " + dp);
        }

        //6.关闭SqlSession对象
        sqlSession.close();

    }
}
