package com.exercise.基础知识题;

public class 基础知识题 {
    /*
    （1）jdbc编程步骤中，核心API有哪些？
               1. DriverManager:驱动管理器
               2. Connection: 代表连接对象
               3. Statement: 执行sql语句对象
               4. ResultSet: 结果集



    （2）请至少写出每个核心类的两个常用方法？
               1. DriverManager:驱动管理器
                  + 注册驱动:  `public static void  registerDriver(Driver driver) ;`
                  + 获得连接:  `public static Connection getConnection(String url,String user,String password);`
               2. Connection: 代表连接对象
                  + 创建执行sql语句对象:`public Statement createStatement();`
                  + 创建预编译sql语句对象:`PreparedStatement prepareStatement(String sql);`
                  + 事务操作:
                     + `void setAutoCommit(boolean autoCommit) false--手动开启事务(start transaction)`
                     + `void commit();提交事务(commit)`
                     + `void rollback();回滚事务(rollback)`
               3. Statement: 执行sql语句对象
                  + 执行查询  		`Result executeQuery(String sql)  返回结果集`
                  + 执行增删改      `int excuteUpdate(String sql)   返回受影响的行数`
               4. ResultSet: 结果集
                  + `boolen next() `       每调用一次, 光标就向下移动一行; 这个行有数据, 返回true; 没有数据, 返回false
                  + `get类型(String 列名);`  根据列名 获得当前列的数据



    （3）什么是SQL注入，如何解决？
               原因: sql语句是做一个简单的字符串拼接,所以会把用户输入的密码中的or关键字注入到sql语句中,从而改变了sql语句的结构
               解决方式: 固定sql语句的格式---->使用预编译sql语句对象,对sql语句的格式进行固定,哪怕后期拼接的内容里面含有关键字,也不会改变sql语句的格式
               PreparedStatement接口:  继承Statement接口
                                作用:  在执行sql语句之前，将sql语句进行提前编译。编译后,  就明确了sql语句的格式，以后就不会再改变了。剩余的内容都会认为是参数！
     */
}
