-- 查询所有数据库
/* 多行注释 */
SHOW databases;

-- 1.使用DDL操作数据库
/*
    DDL操作数据库：
        1.查询所有数据库：show databases;
        2.创建数据库：create database 数据库名称;
        3.删除数据库：drop database 数据库名称;
        4.切换数据库：use 数据库名称;
 */
-- 练习：
-- 1.【重要】查询所有数据库
show databases ;
-- 2.查询数据库的创建语句
show create database day1401;
-- 3【重要】.创建数据库
create database day14;
-- 4.创建数据库【判断 如果不存在则创建 存在则不创建】
create database IF NOT EXISTS day1402;
-- 5.创建数据库 【指定字符集】
create database day1401 character set gbk;
-- 6.修改数据库【修改字符集】
alter database day1401 character set utf8;
-- 7.【重要】删除数据库
drop database day1402;
-- 8.删除数据库【判断，如果存在则删除 不存在就不删除】
drop database IF EXISTS day1402;
-- 9.【重要】切换数据库
use day1401;
-- 10.查看当前使用的数据库
select database();



-- 2.DDL创建表
/*
    创建表语法：
        create table 表名(
            列名 数据类型 约束,
            列名 数据类型 约束,
            ...
            列名 数据类型 约束
        );
        数据类型 ：
            整数：tinyint int bigint
            小数：double
            字符串：char(长度) varchar(长度)
            日期时间：datetime timestamp（当数据类型设置为timestamp时 如果没有设置数据或插入null 则会自动将当前系统时间插入进去）
            注意：插入时间和字符串数据时需要用单引号引起来
       约束：
            主键约束：primary key  一般设置id列为主键类 则该列数据非空并且唯一，从而可以通过主键列唯一的标识一条记录
            自增：在MySQL中 一定是id列数据类型为整型且设置为主键列之后 才能设置自动增长
            唯一约束：要求该列数据都是唯一的 可以有一个数据为null
            非空约束：要求该列数据不能为null
            默认约束：给该类数据设置一个默认值  如果不插入数据或设置为null 则使用默认值

 */
create table student(
                        id int primary key auto_increment,
                        username varchar(20) not null,
                        password varchar(20) not null,
                        birthday datetime,
                        card_id varchar(18) not null unique,
                        address varchar(50) default '深圳'
);


-- 3.DDL查看表
-- 练习：
-- 1.查询所有的数据表
use day1401;
show tables;
-- 2.查询表结构
desc student;
-- 3.查询表的创建信息
show table status from day1401 like 'student';

-- 4.DDL修改表结构
/*
    原因：实际工作中，一般大的公司都有专业的DBA（数据库管理员），负责管理数据库  表的创建啥的都是由DBA设计完成的，基本上一旦表已经创建好了之后几乎不会再进行修改表结构了。
 */
-- 练习：
-- 1.修改表名
alter table student rename to student01;
-- 1.给学生表增加一个grade字段
alter table student01 add grade int;
-- 2.给学生表的grade字段改成字符串类型
alter table student01 modify grade varchar(2);
-- 3.给学生表的grade字段修改成class字段
alter table student01 change grade class int;
-- 4.把class字段删除
alter table student01 drop class;
-- 5.把学生表修改成老师表(了解)
alter table student01 rename to teacher;
-- 6.修改表的字符集
alter table teacher character set GBK;

-- 删除表
drop table teacher;
-- 删除表【判断存在就删除 不存在就不执行操作】
drop table if exists teacher;



-- 5.DML增加记录
-- 准备工作：product表
create table product(
                        pid int primary key auto_increment,
                        pname varchar(40) not null ,
                        price double,
                        num int
);
/*
    语法：
        insert into 表[(列名列表)] values(值列表)[,(值列表)...]

    插入指定列: insert into 表名(列名1,列名2,...)  values(值1,值2,...);
    插入所有列: insert into 表名 values(值1,值2,...);
    -- 注意:
    --  字段名与值的类型、个数、顺序要一一对应。
    --  值不要超出列定义的长度。
    --  插入的日期和字符串，使用单引号括起来。
    --  插入特定的列:没有赋值的列,系统自动赋为null(前提是当前列没有设置not null 约束,否则会报错)
    --  默认所有列插入,values里面必须给表中每一个字段赋值,一般主键给一个null就行了
 */
-- 练习:
-- 练习: 指定pname,price列插入记录
insert into product(pname, price) values ('火鸭',99.8);
insert into product(pname, price,num) values ('火鸭',49.8,10);
-- 练习: 指定所有列插入记录
insert into product values (null,'方便面',5.0,10000);
-- 方式二:批量插入
insert into product values(null,'苹果电脑',18000.0,10),
                          (null,'华为5G手机',30000,20),
                          (null,'小米手机',1800,30),
                          (null,'iPhonex',8000,10),
                          (null,'苹果电脑',8000,100),
                          (null,'iPhone7',6000,200),
                          (null,'iPhone6s',4000,1000),
                          (null,'iPhone6',3500,100),
                          (null,'iPhone5s',3000,100),
                          (null,'方便面',4.5,1000),
                          (null,'咖啡',11,200),
                          (null,'矿泉水',3,500);

-- 6.DML修改记录
/*
    语法：update 表名 set 列1=值1[,列2=值2 ...] where条件
 */
-- 1.不加where条件 会修改表中所有记录 没有意义 容易跑路
update product set price=5,num=100;
-- 2.修改 添加where条件
update product set price=1999,num=1000 where pid=6;
-- 修改表数据练习
-- 1.将所有商品的价格修改为5000元
update product set price=5000;
-- 2.将商品名是Mac的价格修改为18000元
update product set price=18000 where pname='苹果电脑';
-- 3.将商品名是Mac的价格修改为17000,数量修改为5
update product set price=17000,num=5 where pname='苹果电脑';
-- 4.将商品名是方便面的商品的价格在原有基础上增加2元
update product set price=price+2 where pname='方便面';



-- 7.DML删除数据
/*
    语法：
        方式一：delete from 表 where条件
        方式二：truncate table 表                 删除表中全部数据不可恢复
    注意:
        delete 和 truncate区别【笔试题】
        - DELETE 删除表中的数据，表结构还在;   删除的记录可以找回   【通过MySQL日志】
        - TRUNCATE 删除是把表直接DROP掉，然后再创建一个同样的新表(空)。删除的记录不可以找回
        工作里面的删除
        - 物理删除: 真正的删除了, 数据不在, 使用delete就属于物理删除
        - 逻辑删除: 没有真正的删除, 数据还在. 搞一个标记, 其实逻辑删除是更新
                           eg: is_delete字段   0 正常   1 删除
        - 工作里面一般使用逻辑删除用的多

 */
-- 删除表中数据练习
-- 1.删除表中名称为’苹果电脑’的记录
delete from product where pname='苹果电脑';
-- 2.删除价格小于5001的商品记录
delete from product where price<5001;
-- 3.删除表中的所有记录
delete from product;
truncate table product;

-- 8.1:DQL-简单查询
/*
    语法：select 列名列表 from 表
 */
-- 练习：
-- 练习:查询product表中所有的信息
select pid,pname,price,num from product;
select * from product;
-- 练习:查询product表中pname,price字段的值
select pname,price from product;
-- 练习:去重查询pname字段的值
-- 注意:去重查询distinct前面不能有其他字段名
-- 注意：如果distinct关键字后面只有一列 则对当前列相同的数据去重，如果有多列，则对多列相同的数据去重。
select distinct pname from product;
select distinct pname,price from product;
-- 练习:对pname,price取别名查询
select pname AS 商品名称,price 商品价格 from product;
-- 练习: 查询每件商品的总金额
select pname AS 商品名称,(price*num) AS 总金额 from product;

-- 8.2:DQL-条件查询
/*
    语法：select 列名列表 from 表 where条件
 */
-- 练习: 查询price大于4000的商品信息
select * from product where price>4000;
-- 练习: 查询price在4000到8000之间的商品信息
select * from product where price>=4000 and price<=8000;
-- 练习: 查询pid为1,3,5,7,9,11,13的商品信息
select * from product where pid in (1,3,5,7,9,11,13);
-- 练习: 查询商品名称为iPh开头的所有商品信息 模糊查询： %：多个字符  _:单个字符
select * from product where pname like 'iPh%';
-- 练习: 查询商品名称含有手机的所有商品信息  eg: '%手机%'
select * from product where pname like '%手机%';
-- 练习: 查询商品名称为iPh开头,然后iPh后面有4位的所有商品信息  'iPh____'
select * from product where pname like 'iPh____';
-- 练习: 查询price在4000到8000之间的商品信息
-- 注意：between a and b 表示范围查询 包含a和b [a,b] a必须小于等于b
select * from product where price between 4000 and 8000;  -- price>=4000 and price<=8000
-- 练习: 查询price大于4000或者小于1000之间的商品信息
select * from product where price>4000 or price<1000;
-- 练习: 查询pid不为1,3,5,7,9,11,13的商品信息
select * from product where pid not in (1,3,5,7,9,11,13);

-- 8.3:DQL-条件查询
-- 环境准备：
CREATE TABLE student(
                        sid INT PRIMARY KEY auto_increment,
                        sname VARCHAR(40),
                        sex VARCHAR(10),
                        age INT,
                        score DOUBLE
);
-- 插入测试数据
INSERT INTO student VALUES(null,'zs','男',18,98.5);
INSERT INTO student VALUES(null,'ls','女',18,96.5);
INSERT INTO student VALUES(null,'ww','男',15,50.5);
INSERT INTO student VALUES(null,'zl','女',20,98.5);
INSERT INTO student VALUES(null,'tq','男',18,60.5);
INSERT INTO student VALUES(null,'wb','男',38,98.5);
INSERT INTO student VALUES(null,'小丽','男',18,100);
INSERT INTO student VALUES(null,'小红','女',28,28);
INSERT INTO student VALUES(null,'小强','男',21,95);

/*
    语法：
        方式一：单列排序：select 列名列表 from 表 order by 列名 排序规则[ASC|DESC]
        方式二：多列排序：select 列名列表 from 表 order by 列1 排序规则[ASC|DESC],列2 排序规则[ASC|DESC]
        注意：
            1.排序规则 如果不指定排序规则默认使用ASC升序排列，DESC表示降序排列
            2.如果根据多列进行排序时 则只有当第一个排序列的值相同时，才会根据第二列进行排序
 */
-- 1. 练习: 以分数降序查询所有的学生
select * from student order by score DESC ;
-- 2. 练习: 以分数降序查询所有的学生, 如果分数一致,再以age降序
select * from student order by score DESC,age DESC ;

-- 8.4:DQL-聚合函数  作用：用于数学统计    max() min() avg() sum() count()
-- 练习:求出学生表里面的最高分数
select max(score) from student;
-- 练习:求出学生表里面的最低分数
select min(score) from student;
-- 练习:求出学生表里面的分数的总和
select sum(score) from student;
-- 练习:求出学生表里面的平均分
select avg(score) from student;
-- 练习:统计学生的总人数
select count(score) from student;
-- 修改: 把sname为wb的score修改为null
update day1401.student set score=null where sname='wb';
-- 练习:统计学生的总人数
select count(score) from student;
-- 原因：是聚合函数会忽略空值null
-- 统计表中的总记录条数
select count(sid) from student;
select count(*) from student;

-- 8.5:DQL-分组查询
/*
    语法: select ... from 表名 [where条件] [group by 分组字段] [having条件]
    注意：
        1.**单独分组 没有意义,因为 返回每一组的第一条记录**
            eg:select sex from 表 group by sex;
        2.**分组的目的一般为了做统计使用, 所以经常分组和聚合函数一起使用**
            eg:select sex,count(*) from 表 group by sex;
        3.**分组查询如果不查询出分组字段的值,就无法得知结果属于那组**  1.分组查询的数据一般是分组列和聚合函数
            eg:select count(*) from 表 group by sex;
    where条件和having条件的区别：
        1.where条件用在from 表后面 对当前表中数据进行筛选查询 where后面不能使用聚合函数
        2.having条件用在  分组后面 对分组后的统计结果进行筛选，可以使用聚合函数

 */
select sex from student group by sex;
select sex,count(*) from student group by sex;
select count(*) from student group by sex;
-- 练习:
-- 1. 练习:根据性别分组,统计男生的总人数和女生的总人数
select  count(*) from  day1401.student;
select sex,count(*) from day1401.student group by sex;
-- 2. 练习根据性别分组, 统计每一组学生的总人数> 5的(分组后筛选)
select sex,count(*) from day1401.student group by sex having count(*)>5;

-- 3.统计年龄大于18岁的男生和女生的总人数
select * from day1401.student where age>18;

select sex,count(*) from day1401.student
where age > 18
group by sex;

-- 8.6:DQL-分页查询
/*
    语法：
        select * from 表 limit a,b
            a：当前页起始条数据下标     a=(currentPage-1)*pageSize
            b：每页显示条数            b=pageSize
        分页查询需要两个条件：
            当前页码：       currentPage
            每页显示条数：    pageSize
 */
-- 需求1：每页显示4条，查询第一页的数据  (1-1)*4
select * from day1401.student limit 0,4;
select * from day1401.student limit 4;    -- 查询第一页数据 起始条数据下标a可以省略 一旦省略默认查询的就是第一页的数据
-- 需求2：每页显示4条，查询第二页的数据  (2-1)*4
select * from day1401.student limit 4,4;
-- 需求3：每页显示4条，查询第三页的数据  (3-1)*4
select * from day1401.student limit 8,4;

-- 需求4：每页显示5条，查询第一页
select * from day1401.student limit 5;
-- 需求5：每页显示5条，查询第二页
select * from day1401.student limit 5,5;
-- 需求6：每页显示5条，查询第三页
select * from day1401.student limit 10,5;



















