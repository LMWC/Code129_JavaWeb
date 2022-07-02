package demo02_初级版本;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyDataSource {
    private static LinkedList<Connection> pool;

    //静态代码块初始化静态成员变量  程序一运行，就执行静态代码块 只执行一次
    static {
        pool = new LinkedList<>();

        try {
            //1.导入数据库驱动jar包
            //2.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //创建连接对象 存入到连接池集合中
        for (int i = 0; i < 5; i++) {
            try {
                //3.获取连接对象
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC","root","root");
                //4.将连接对象 存入连接池中
                pool.addLast(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    //提供获取连接对象的方法【连接池的核心方法】
    public Connection getConn(){
        //删除LinkedList集合中的第一个元素 将第一个连接对象给出去使用
        return pool.removeFirst();
    }

    //提供归还连接对象的方法【连接池的核心方法】
    public void goHome(Connection conn){
        //归还连接对象  实际上就是将连接对象重新存入到LinkedList集合中
        pool.addLast(conn);
    }

    //提供统计连接池中连接个数的方法【可有可无】
    public int count(){
        return pool.size();
    }
}
