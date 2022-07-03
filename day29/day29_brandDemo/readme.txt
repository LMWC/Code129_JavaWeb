综合案例环境搭建：
	1.创建数据库 创建表 插入测试数据
	2.创建Maven工程  添加依赖坐标【可以在之前的坐标基础上加上fastjson依赖】
	3.添加页面【html、css、js】 放入webapp目录下
	4.创建项目包结构及javabean
		com.itheima.bean:Brand
		com.itheima.dao:BrandDao
		com.itheima.service:BrandService
		com.itheima.web:XxxServlet
		com.itheima.utils：工具类
	5.添加配置文件和工具类
	6.编写代码实现功能
		dao-->service-->servlet
	7.测试功能

修改品牌信息实现思路：
    1.前端html
        1.注意：在updateBrand()函数中发起修改品牌信息的ajax请求
        2.编写updateBrand()函数代码
            2.1：发送ajax请求 携带要修改品牌信息
                axios.post("/brand/update",this.ruleForm).then(response=>{});
            2.2：处理响应数据 返回true|false
                true：修改成功 重新查询所有展示
                false：修改失败 弹窗提示
                不管修改成功还是失败，都需要隐藏对话框
    2.后台java
        1.编写BrandServlet的update方法
            获取请求参数
            调用业务处理
            响应数据 true|false
        2.编写BrandService的update方法
        3.编写BrandDao的update方法

删除品牌实现思路：
    1.前端html
        1.注意：在handleDelete()函数中发起删除品牌的ajax请求
        2.编写handleDelete()函数代码
            2.0：需要从row中获取要删除的品牌id
            2.1：发送ajax请求 携带要删除品牌的id
                axios.post("/brand/delete","id="+id).then(response=>{});
            2.2：处理响应数据 返回true|false
                true：删除成功  弹窗提示 重新查询所有展示
                false：删除失败 弹窗提示
    2.后台java
        1.编写BrandServlet的delete方法
            获取请求参数【要删除的品牌id    request.getParameter("id")】
            调用业务处理
            响应数据 true|false
        2.编写BrandService的delete方法
        3.编写BrandDao的delete方法
