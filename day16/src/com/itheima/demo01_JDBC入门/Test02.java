package com.itheima.demo01_JDBC入门;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * JDBC API详解
 */
public class Test02 {

    /*
        JDBC API：
            DriverManager类：驱动管理者
                1.注册驱动
                    如果使用DriverManager.registerDriver(new Driver()); 导致驱动注册了2遍，没有必要【原因就是Driver类中提供的有静态代码块进行驱动注册】
                    Class.forName("com.mysql.jdbc.Driver");     //使用反射注册驱动  通过反射技术将Driver加载到JVM中，就会执行Driver类的静态代码块 完成驱动注册  注册一次
                    注意：关于驱动注册 可以进行显示注册驱动 也可以不用显式注册【就是无需写代码完成驱动注册 因为MySQL5.0之后根据java服务提供接口 提供了一个java.sql.Driver配置文件 里面配置了jar包加载到JVM时要加载的类com.mysql.jdbc.Driver】
                2.获取连接对象
                    public static Connection getConnection(String url,String user, String password)
                        url：数据库服务器所在地址     eg：jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
                        user：数据库用户名           eg：root
                        password：数据库密码        eg：root
                        数据库连接URL参数：
                            useSSL=false&serverTimezone=UTC
                            useSSL：告诉连接数据库时不使用SSL安全协议  eg：https=http+ssl
                            serverTimezone：设置时区 MySQL高版本要求要设置时区 不设置 连接不上数据库
                            eg：https://blog.csdn.net/shawCloud/article/details/104714663

            Connection接口：连接
                1.获取执行者对象
                    connection.createStatement()：获取普通的执行者对象
                    connection.prepareStatement(String sql)：获取预编译执行者对象  需要提前传入sql语句
                2.管理事务
                    1.开启事务
                        connection.setAutocommit(false); //关闭事务自动提交 开启事务手动提交
                    2.执行操作
                    3.执行成功：connection.commit();
                      执行失败：connection.rollback();
                3.关闭对象
                    connection.close();

            Statement接口：执行者【执行SQL】
                1.执行增删改查SQL语句
                    执行增删改：int executeUpdate(String sql);  //这里的sql语句是insert、delete、update 返回的是受影响的行数int数字
                    执行查询： ResultSet executeQuery(String sql); //这里的sql语句是select  返回的是查询得到的结果集封装到ResultSet对象中
                2.关闭对象
                    statement.close();

            ResultSet接口：结果集
                作用：封装查询结果集
                1.读取结果集
                    next()：判断下一行是否还有数据  有数据返回true指针向下移动一行 可以继续读  没有数据返回false
                    getXxx(String columnName)|geetXxx(int columnIndex)：Xxx表示列的数据类型，如果你不清楚 可以使用Object，列的索引columnIndex从1开始
                2.关闭对象
                    resultset.close();
                注意：一般读取结果集是放在while循环中读取，实际情况下不清楚结果集中有多少条记录，当然 如果你明确的知道返回的数据只有一行，也可以使用if
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
            需求：使用JDBC查询用户表中的所有记录
            JDBC操作步骤 ：
                1.导入数据库驱动jar包
                2.加载驱动
                3.获取数据库连接对象
                4.获取执行者【执行SQL语句】对象
                5.执行SQL语句，得到返回结果
                6.处理返回结果
                7.关闭对象 释放资源
         */
        //2.加载驱动
        //DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.jdbc.Driver");     //使用反射注册驱动  通过反射技术将Driver加载到JVM中，就会执行Driver类的静态代码块 完成驱动注册  注册一次

        //3.获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC","root","root");

        //4.获取执行者【执行SQL语句】对象
        Statement statement = connection.createStatement();


        //5.执行SQL语句，得到返回结果
        ResultSet resultSet = statement.executeQuery("select * from user");

        //6.处理返回结果
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id")+" "+resultSet.getString("username")+" "+resultSet.getString("password")+" "+resultSet.getString(4));
        }


        //7.关闭对象 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
