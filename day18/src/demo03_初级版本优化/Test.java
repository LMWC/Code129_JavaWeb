package demo03_初级版本优化;

import com.itheima.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    /*
        实现步骤
            - 创建连接池类(MyDataSource)
            - 在连接池类中,定义一个LinkedList集合成员变量(表示连接池)
            - 在连接池类的静态代码块中,创建固定数量个连接,并存储到LinkedList集合中
            - 在连接池类中定义一个公共的非静态的成员方法,用来获取连接(删除集合中第一个元素)
            - 在连接池类中定义一个公共的非静态的成员方法,用来归还连接(添加元素到集合的末尾)
            - 在连接池类中定义一个公共的非静态方法用来获取连接池中的连接数量
     */

    public static void main(String[] args) throws SQLException {
        //1.创建连接池类对象
        MyDataSource dataSource = new MyDataSource();

        //2.获取连接对象
        Connection conn = dataSource.getConn();

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
        //conn.close();//连接对象 原有的close方法表示销毁

        //连接对象是要归还到连接池中
        dataSource.goHome(conn);

        System.out.println("归还后-连接池中连接对象个数"+dataSource.count());
    }

}
