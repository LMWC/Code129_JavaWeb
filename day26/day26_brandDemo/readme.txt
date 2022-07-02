三层架构实现品牌增删改查案例环境搭建：
	1.准备数据库 创建表 插入测试数据
	2.创建javaweb Maven工程  添加依赖
	3.创建项目的包结构
		com.itheima.web：表现层
			XxxServlet类
				1.获取请求参数
				2.调用业务处理
				3.响应
		com.itheima.service：业务逻辑层
			XxxService类
				1.处理业务
				2.调用dao
		com.itheima.dao：数据访问层
			XxxDao接口
				1.操作数据库
		com.itheima.bean：存放javabean 封装数据的标准java类  eg:User、Brand
		com.itheima.utils：存放工具类
	4.创建数据库表对应的实体类 存放到bean包下
	5.添加MyBatis相关配置文件
		mybatis-config.xml
		log4j.properties
		db.properties
	6.创建dao接口和dao映射文件
	7.添加工具类
	8.添加页面
	9.运行测试