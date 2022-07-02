package com.itheima.demo03_JDBC事务;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 需求：张三给李四转账100元
 * 使用JDBC事务完成转账
 *  转账业务功能实现：
 *      操作数据库 zs-100 ls+100
 *
 *  注意：
 *      事务是和Connection对象绑定到一起的，也就是一个事务对应一个Connection对象，所以 一个事务下的多个操作要由同一个connection对象获取执行者对象执行
 *  事务使用步骤：
 *      1.开启事务 connection.setAutoCommit(false);
 *      2.执行操作
 *      3.1：执行成功         提交事务   connection.commit();
 *      3.2：执行失败|出现异常 回滚事务   connection.rollback();
 *
 */
public class Test02 {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC", "root", "root");

            /*================================1.开启事务=============================*/
            connection.setAutoCommit(false);

            /*================================2.执行操作=============================*/
            //3.获取预编译执行者对象
            //3.1:zs钱减少
            String sql01 = "update account set money=money-? where name=?";
            PreparedStatement pst01 = connection.prepareStatement(sql01);

            pst01.setInt(1,100);
            pst01.setString(2,"zs");

            //3.2：ls钱增加
            String sql02 = "update account set money=money+? where name=?";
            PreparedStatement pst02 = connection.prepareStatement(sql02);

            pst02.setInt(1,100);
            pst02.setString(2,"ls");

            //4.执行操作
            int rows01 = pst01.executeUpdate();

            //人为制造的异常
            //int i = 1/0;

            int rows02 = pst02.executeUpdate();

            //5.处理返回结果
            if(rows01>0 && rows02>0){
                /*================================3.1：执行成功 提交事务============================*/
                connection.commit();
                System.out.println("转账成功");
            }else{
                /*================================3.2：执行失败 回滚事务============================*/
                connection.rollback();
                System.out.println("转账失败！");
            }


            //6.关闭对象 释放资源
            pst01.close();
            pst02.close();
            connection.close();

        } catch (Exception e) {
            /*================================3.2：执行失败 回滚事务============================*/
            connection.rollback();
            System.out.println("转账失败！");
            e.printStackTrace();
        }
    }
}
