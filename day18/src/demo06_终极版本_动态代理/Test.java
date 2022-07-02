package demo06_终极版本_动态代理;

import com.itheima.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    /*
        实现步骤
            -    定义一个类，实现 DataSource 接口
                 定义一个连接池容器，用于保存多个Connection连接对象
                 定义静态代码块，创建5个连接对象保存到容器中
                 重写 getConnection 方法，从容器中获取一个连接，对获取的connection进行增强
                 对connection使用动态代理，如果是 close 方法，就将连接归还池中；如果是其他方法则调用原有功能。

     */

    public static void main(String[] args) throws SQLException {
        //1.创建连接池类对象
        MyDataSource dataSource = new MyDataSource();

        //2.获取连接对象
        Connection conn = dataSource.getConnection();

        //3.使用连接对象获取执行者对象
        String sql = "select * from user where id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,3);


        //4.执行SQL语句，得到返回结果
        ResultSet resultSet = pst.executeQuery("select * from user where id=3");

        //5.处理返回结果
        User user = null;
        while (resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setNickname(resultSet.getString("nickname"));
        }

        System.out.println("user = " + user);

        System.out.println("归还前-连接池中连接对象个数"+dataSource.count());

        //6.关闭对象 释放资源
        resultSet.close();
        pst.close();
        conn.close();//将连接对象归还到连接池中


        System.out.println("归还后-连接池中连接对象个数"+dataSource.count());
    }

}
