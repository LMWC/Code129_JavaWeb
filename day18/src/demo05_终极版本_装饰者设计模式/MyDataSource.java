package demo05_终极版本_装饰者设计模式;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {
    private static LinkedList<Connection> pool;

    //静态代码块初始化静态成员变量  程序一运行，就执行静态代码块 只执行一次
    static {
        pool = new LinkedList<>();

        FileInputStream fis = null;
        String driverClass = null;
        String url = null;
        String username = null;
        String password = null;

        //读取db.properties配置文件中的数据库连接信息
        try {
            //1.获取db.properties文件的流
            fis = new FileInputStream("day18_code//src//db.properties");

            //2.将流加载到properties对象中
            Properties properties = new Properties();
            properties.load(fis);

            //3.使用getProperty()方法根据properties文件中的key获取value
            driverClass = properties.getProperty("jdbc.driverClass");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //注册驱动
        try {
            //1.导入数据库驱动jar包
            //2.注册驱动
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //创建连接对象 存入到连接池集合中
        for (int i = 0; i < 5; i++) {
            try {
                //3.获取连接对象
                Connection conn = DriverManager.getConnection(url,username,password);
                //4.将连接对象 存入连接池中
                pool.addLast(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    //提供获取连接对象的方法【连接池的核心方法】
    @Override
    public Connection getConnection() throws SQLException {
        //要增强的连接对象
        Connection connection = pool.removeFirst();

        //将连接对象增强  返回增强后的连接对象
        return new MyConnection(pool,connection);
    }
    /*public Connection getConn(){
        //删除LinkedList集合中的第一个元素 将第一个连接对象给出去使用
        return pool.removeFirst();
    }*/

    //提供归还连接对象的方法【连接池的核心方法】
    /*public void goHome(Connection conn){
        //归还连接对象  实际上就是将连接对象重新存入到LinkedList集合中
        pool.addLast(conn);
    }*/

    //提供统计连接池中连接个数的方法【可有可无】
    public int count(){
        return pool.size();
    }



    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
