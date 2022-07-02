package demo05_终极版本_装饰者设计模式;

import com.itheima.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    /*
        增强Connection对象的close方法功能-使用装饰者设计模式
            定义一个类，实现 Connection 接口。
            重写 close() 方法，完成连接的归还。

        实现步骤
             定义一个类 eg：MyConnection，实现 Connection 接口
             在MyConnection类中定义 Connection 连接对象和连接池容器对象LinkedList  的成员变量
             通过有参构造方法完成对成员变量的赋值
             重写 close() 方法，将连接对象添加到连接池中
             剩余方法，只需要调用 原有的connection对象(mysql 驱动包的连接对象)完成即可
             在自定义连接池中，将获取的连接对象通过自定义连接对象进行包装增强

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
        conn.close(); //将连接对象归还到连接池中

        System.out.println("归还后-连接池中连接对象个数"+dataSource.count());
    }

}
