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

批量删除实现思路：
    1.前端html：
        注意：点击批量删除按钮时发送请求
            <el-button type="danger" plain @click="deleteByIds()">批量删除</el-button>
        1.1：获取请求参数【批量删除的品牌id数组】
            a：声明数据模型 绑定复选框选中的品牌记录数组
                //表格复选框选中的品牌记录
                multiRecords:[],
                //表格复选框多选事件函数
                handleSelectionChange(val) {
                    console.log(val);
                    this.multiRecords = val;
                }
            b.在删除按钮事件函数deleteByIds中，获取要删除的品牌id数组
                遍历选中的品牌记录，得到品牌记录的id存放到id数组中ids
            c.判断数组不为null，长度要大于0
        1.2：发送ajax请求，携带ids数组  ids数组=json数组
            axios.post("/brand/deleteByIds",ids).then(...);
        1.3：处理响应数据 true|false
            true：删除成功  弹窗提示 重新查询所有展示
            false：删除失败 弹窗提示

    2.java后台：
        1.编写BrandServlet的deleteByIds方法
            获取请求参数【要删除的品牌id数组 使用JSON.parseObject(request.getInputStream(),int[].class)】
            调用业务处理
            响应数据 true|false
        2.编写BrandService的deleteByIds方法
        3.编写BrandDao的deleteByIds方法

分页的实现：实际上就是做了数据的查询 根据用户在页面指定的当前页码和每页显示条数 去数据库中查询出指定的数据展示
数据库分页查询：  当前页码：currentPage   每页显示条数：pageSize
    -- 查询tb_brand表中第一页的数据 每页显示2条
        select * from tb_brand limit 0,2;
    -- 查询tb_brand表中第二的数据 每页显示2条
        select * from tb_brand limit 2,2;
    -- 查询tb_brand表中第三的数据 每页显示2条
        select * from tb_brand limit 4,2
    语法：select * from 表 limit a,b       查询出指定页码显示数据
        a：当前页起始条数据下标    (当前页码-1)*每页显示条数   (currentPage-1)*pageSize
        b：每页要显示的条数        pageSize

    总页数：总条数%每页显示条数==0?总条数/每页显示条数:总条数/每页显示条数+1   总页数：pages   总条数：total
            pages = total%pageSize==0?total/pageSize:total/pageSize+1;
            示例：表中有5条记录，每页显示2条，总页数为3     每页显示3条 总页数为2
    查询总条数：
        查询表中全部记录【无脑查询】：select count(*) from 表
        根据条件查询记录，统计条数：select count(*) from 表 where条件


    查询每页显示条数：select * from 表 limit (currentPage-1)*pageSize,pageSize
    查询总条数：select count(*) from 表 [where条件]


分页查询思路分析：
    1.前端html：
            注意：在vue的生命周期created|mounted阶段  或者 当页码或每页显示条数发生改变时
            1.1：获取请求参数【当前页码：currentPage  每页显示条数：pageSize】
            1.2：发送ajax请求，携带当前页码和每页显示条数
                axios.get("/brand/selectByPage?currentPage="+this.currentPage+"&pageSize="+this.pageSize).then(...);

                eg：
                    axios.get("/brand/selectByPage?currentPage=2&pageSize=5").then(...);
                    实际开发：有多个参数传递，一般会封装到一个json对象中
                        前端：
                            var pageParam={
                                "currentPage":this.currentPage,
                                "pageSize":this.pageSize
                            };
                            axios.post("/brand/selectByPage",pageParam).then(...);
                        后端：自定义一个类PageParam 来接收请求参数
                            PageParam pageParam = JSON.parseObject(request.getInputStream(),PageParam.class);

            1.3：处理响应数据 PageBean的json对象
                进行数据绑定：
                    当前页数据展示：tableData
                    总条数：total

        2.java后台：
            1.编写BrandServlet的selectByPage方法
                获取请求参数【当前页码：currentPage  每页显示条数：pageSize】
                    万一前端没有传递当前页码和每页显示条数  设置一个默认的当前页码和每页显示条数
                调用业务处理 返回PageBean对象【封装当前页数据list和总条数total】
                响应json数据
            2.编写BrandService的selectByPage方法
                1.调用dao查询当前页数据list和总条数total
                2.将当前页数据list和总条数total封装到PageBean对象中并返回
            3.编写BrandDao
                selectByPageList(int offset,int pageSize)方法：查询当前页数据  offset：当前页起始条数据下标
                selectByPageTotal()方法：查询总条数


条件查询分页展示思路分析：
    1.前端html：
            注意：点击查询按钮时发起分页查询请求 或者 在vue的生命周期created|mounted阶段  或者 当页码或每页显示条数发生改变时
            1.1：获取请求参数【当前页码：currentPage  每页显示条数：pageSize 查询条件Brand对象==>formInline数据模型】
            1.2：发送ajax请求，携带当前页码和每页显示条数，还有查询条件
                axios.post("/brand/selectByPageOnCondition?currentPage="+this.currentPage+"&pageSize="+this.pageSize,this.formInline).then(...);

                eg：
                    实际开发：有多个参数传递，一般会封装到一个json对象中
                        前端：
                            var pageParam={
                                "currentPage":this.currentPage,
                                "pageSize":this.pageSize,
                                "brand":this.formInline
                            };
                            axios.post("/brand/selectByPageOnCondition",pageParam).then(...);
                        后端：自定义一个类PageParam 来接收请求参数
                            PageParam pageParam = JSON.parseObject(request.getInputStream(),PageParam.class);

            1.3：处理响应数据 PageBean的json对象
                进行数据绑定：
                    当前页数据展示：tableData
                    总条数：total

        2.java后台：
            1.编写BrandServlet的selectByPageOnCondition方法
                获取请求参数【当前页码：currentPage  每页显示条数：pageSize,查询条件 brand  将请求参数封装到PageParam对象中】
                    万一前端没有传递当前页码和每页显示条数  设置一个默认的当前页码和每页显示条数
                调用业务处理 返回PageBean对象【封装当前页数据list和总条数total】
                响应json数据
            2.编写BrandService的selectByPageOnCondition方法
                1.调用dao根据条件查询当前页数据list和总条数total
                2.将当前页数据list和总条数total封装到PageBean对象中并返回
            3.编写BrandDao
                selectByPageOnConditionList(PageParam pageParam)方法：查询当前页数据  offset：当前页起始条数据下标
                selectByPageOnConditionTotal(PageParam pageParam)方法：查询总条数