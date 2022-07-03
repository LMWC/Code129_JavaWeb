create database day15;

-- 切换数据库
use day15;

CREATE TABLE emp (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     NAME VARCHAR(30),
                     age INT,
                     dep_name VARCHAR(30),
                     dep_location VARCHAR(30)
);
-- 添加数据
INSERT INTO emp (NAME, age, dep_name, dep_location) VALUES ('张三', 20, '研发部', '广州');
INSERT INTO emp (NAME, age, dep_name, dep_location) VALUES ('李四', 21, '研发部', '广州');
INSERT INTO emp (NAME, age, dep_name, dep_location) VALUES ('王五', 20, '研发部', '广州');
INSERT INTO emp (NAME, age, dep_name, dep_location) VALUES ('老王', 20, '销售部', '深圳');
INSERT INTO emp (NAME, age, dep_name, dep_location) VALUES ('大王', 22, '销售部', '深圳');
INSERT INTO emp (NAME, age, dep_name, dep_location) VALUES ('小王', 18, '销售部', '深圳');

-- 1.表的拆分：
-- 作用：通过将一张表拆分成多张表 可以解决数据冗余问题，以及方便对表中的数据进行维护。
-- 创建部门表
CREATE TABLE department (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            dep_name VARCHAR(20),
                            dep_location VARCHAR(20)
);
-- 创建员工表
CREATE TABLE employee (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          NAME VARCHAR(20),
                          age INT,
                          dep_id INT
);

-- 添加2个部门
INSERT INTO department (dep_name, dep_location) VALUES ('研发部', '广州');
INSERT INTO department (dep_name, dep_location) VALUES('销售部', '深圳');

-- 添加员工,dep_id表示员工所在的部门
INSERT INTO employee (NAME, age, dep_id) VALUES
('张三', 20, 1),
('李四', 21, 1),
('王五', 20, 1),
('老王', 20, 2),
('大王', 22, 2),
('小王', 18, 2);

-- 2.外键：
/*
    概念：一张从表的某个字段作为外键，指向主表的主键，只有当主表的主键列存在对应的数据，从表的外键列才能使用
    作用：用来维护多表之间的关系
 */
-- 2.1：为已有表添加外键 注意：从表外键列上不能由非法数据
/*
    语法：alter table 从表 add [constraint 外键约束名称] foregin key(外键列) references 主表(主键列)
*/
alter table employee add constraint fk_dep_id foreign key(dep_id) references department(id);

-- 2.2：删除外键
/*
    语法：alter table 从表 drop foreign key 外键名称;
 */
alter table employee drop foreign key fk_dep_id;

-- 删除表
drop table employee;
drop table department;

-- 创建表时添加外键
-- 创建部门表【主表 主键id】
CREATE TABLE department (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            dep_name VARCHAR(20),
                            dep_location VARCHAR(20)
);
-- 创建员工表【从表 外键dep_id】
CREATE TABLE employee (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          NAME VARCHAR(20),
                          age INT,
                          dep_id INT,
                          foreign key (dep_id) references department(id)
);

-- 存在主外键的表插入数据：先插入主表数据  后插入从表数据
-- 添加2个部门
INSERT INTO department (dep_name, dep_location) VALUES ('研发部', '广州');
INSERT INTO department (dep_name, dep_location) VALUES('销售部', '深圳');

-- 添加员工,dep_id表示员工所在的部门
INSERT INTO employee (NAME, age, dep_id) VALUES
('张三', 20, 1),
('李四', 21, 1),
('王五', 20, 1),
('老王', 20, 2),
('大王', 22, 2),
('小王', 18, 2);

-- 存在主外键的表删除数据：先删从表数据 再删主表数据
-- 删除员工表部门编号为2的员工
delete from day15.employee where day15.employee.dep_id=2;
delete from day15.department where id=2;

-- 删除外键
alter table employee drop foreign key employee_ibfk_1;

-- 2.3:级联外键 级联删除和更新 【了解】
alter table employee
    add constraint employee_ibfk_1
        foreign key(dep_id) references department(id)
            on update cascade       -- 级联更新
            on delete cascade ;     -- 级联删除

-- 级联更新
update department set id=5 where id=2;
-- 级联删除
delete from department where id=1;

-- 3.多表间关系
/*
    建表原则：
        一对多|多对一：在多方添加一列字段，作为外键，指向一方的主键      eg：公司-员工
        多对多：单独创建一张中间表，用于存储多对多关系，在这种中间表中至少有两个字段作为外键，分别指向各自表的主键  eg：学生-课程
        一对一：一般情况下，一对一关系就直接创建一张表即可
            唯一外键：在其中一种表增加一列字段作为外键指向另一张表的主键，同时设置外键列唯一约束      eg：公司-地址
            主键外键：设置其中一张表的主键也是外键                                            eg：公司-地址
*/
/*
    需求：完成一个学校的选课系统，在选课系统中包含班级、学生和课程这些实体。
    分析：
        1.需要建几张表？
            班级表
            学生表
            课程表
        2.分析多表间的关系
            班级表-学生表 :  1对多
            班级表-课程表 : 没有
            学生表-课程表 : 多对多       需要额外创建一张中间表，至少需要有两个字段 分别指向学生表id和课程表id
        3.最终：需要创建四张表
            班级表
            学生表：在学生表中增加一列字段作为外键指向班级表的主键
            课程表
            学生课程中间表
*/
-- 班级表
create table t_class(
                        id int primary key auto_increment,
                        classname varchar(20) not null
);

-- 学生表
create table t_student(
                          id int primary key auto_increment,
                          stuname varchar(20) not null,
                          cid int,
                          foreign key(cid) references t_class(id)
);

-- 课程表
create table t_course(
                         id int primary key auto_increment,
                         coursename varchar(20) not null
);

-- 学生课程中间表
create table t_student_course(
                                 sid int,
                                 cid int,
                                 foreign key(sid) references t_student(id),
                                 foreign key(cid) references t_course(id)
);



-- 4.连接查询
create database day1502;

-- 切换数据库
use day1502;

-- 创建部门表
CREATE TABLE dept (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(20)
);
INSERT INTO dept (name) VALUES ('开发部'),('市场部'),('财务部');
-- 创建员工表
CREATE TABLE emp (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     name VARCHAR(10),
                     gender CHAR(1),   -- 性别
                     salary DOUBLE,   -- 工资
                     join_date DATE,  -- 入职日期
                     dept_id INT
);
INSERT INTO emp(name,gender,salary,join_date,dept_id) VALUES('孙悟空','男',7200,'2013-02-24',1);
INSERT INTO emp(name,gender,salary,join_date,dept_id) VALUES('猪八戒','男',3600,'2010-12-02',2);
INSERT INTO emp(name,gender,salary,join_date,dept_id) VALUES('唐僧','男',9000,'2008-08-08',2);
INSERT INTO emp(name,gender,salary,join_date,dept_id) VALUES('白骨精','女',5000,'2015-10-07',3);
INSERT INTO emp(name,gender,salary,join_date,dept_id) VALUES('蜘蛛精','女',4500,'2011-03-14',1);

-- 4.1：交叉连接查询
-- 查询数据：select 字段列表 from 表名
-- 问题来了：现在我想同时查询部门表和学生表两张表中的数据？  --请使用连接查询
-- 注意：同时查询多张表数据时，如果两张表中有相同的字段名称，查询时需要使用表名进行区分，如果不存在相同的列名，则可以直接写
-- 1. 练习：使用交叉查询部门和员工的信息
select * from dept,emp; -- 15条记录： dept：3 * emp：5 = 15
-- 2. 练习: 使用交叉查询部门的名称和员工id,名称,salary,join_date信息

select dept.name,emp.id,emp.name,salary,join_date from dept,emp;

-- 为表起别名
select d.name,e.id,e.name,salary,join_date from dept AS d,emp AS e;

-- 4.2：内连接查询
/*
    概念：查询两张表中共同拥有的数据 也就是取交集【公共部分】
    语法：
        1.隐式内连接
            select 字段列表 from 主表,从表  where 主表.主键=从表.外键;
            或
            select 字段列表 from 从表,主表  where 主表.主键=从表.外键;
        2.显式内连接【推荐】
            select 字段列表 from 主表
                    inner join 从表
                    on 主表.主键=从表.外键;
        注意：内连接查询，对于两张表放的位置没有要求，可以互相颠倒
*/

-- 需求：查询员工所属部门信息 要求显示员工姓名和部门名称
select emp.name,dept.name from dept,emp where dept.id=emp.dept_id;

-- 练习: 查询员工的id,姓名,性别,薪资,加入日期,所属部门
select emp.id,emp.name,gender,salary,join_date,dept.name from dept
                                                                  inner join emp
                                                                             on dept.id = emp.dept_id;

-- 4.3：外连接
/*
    左外连接：
        概念：查询出左边表中的所有数据，让右表中的数据进行匹配填充，存在就正常填充，不存在就填充null。
        简而言之：就是在内连接的基础上要显示左边表中所有数据
        语法：select 字段列表 from 左表 left join 右表 on 主表.主键=从表.外键;
        注意：左边作为主表，会将左边中的所有数据都查询出来
    右外连接：
        概念: 右边表的数据全部显示出来. 再通过连接条件匹配出左边表的数据, 如果满足连接条件, 展示左边表的数据; 如果不满足,左边表的数据通过null代替。。
        简而言之：在内连接的基础上保证右边表的数据全部显示
        语法: select 字段列表 from 左表名 right join 右表名 on 主表.主键=从表.外键;
        注意：右边表作为主表 会将右边表中的所有数据都查询出来
    注意：
        1.左外连接 使用left join 左边表作为主表
        2.右外连接 使用right join 右表表作为主表
        3.左外连接和右外连接可以相互替换【就是谁作为主表的问题】
    技巧：
        1.如果要查询两张表中共同拥有数据就用内连接
        2.如果要查询某张表中的全部数据就用外连接

*/

-- 练习1：查询所有部门下的员工，没有员工就显示null
-- 分析：查询出部门表中所有的数据  让员工表中的数据进行匹配填充 匹配不上填充null
select d.id,d.name,e.name from dept d
                                   left join emp e
                                             on d.id = e.dept_id
order by d.id;

select d.id,d.name,e.name from emp e
                                   right join dept d
                                              on d.id = e.dept_id
order by d.id;

-- 练习2:查询所有员工所对应的部门,没有部门就显示null
-- 分析：查询所有员工信息 -->使用外连接 -->员工表作为主表
select e.name,d.name from dept AS d
                              right join emp AS e
                                         on d.id = e.dept_id
order by e.id;

-- 5.子查询
/*
    子查询：在一个条SQL语句内部嵌套了一条查询语句，这条查询语句就称为子查询。
    子查询的应用场景：
        主要用在insert 、 update 、delete 、select 这些语句中，一般是用在where条件中 或 查询的from后面
    子查询返回结果：
        1.返回单个值     用在where条件中，和比较运算符(> < >= <= <> =)一起使用
        2.返回单列多行    用在where条件中，和in一起使用
        3.返回多列多行    用在from后面，作为一张虚拟的表
            SELECT ... FROM （子查询） 表别名 WHERE 条件;`
            注意：子查询作为表需要取别名，否则这张表没用名称无法访问表中的字段
    子查询的实现技巧：
        1.分析 先分析内部子查询语句  再分析外部父查询语句
        2.子查询语句要放在小括号()中
 */
-- 5.1:返回单个值
-- 1.查询工资最高的员工是谁？
-- 分析：1.查询出最高工资  2.查询出工资等于最高工资的员工 得到了最高工资的员工了
select max(salary) from emp;

select * from emp where salary=(select max(salary) from emp);

-- 2.查询工资小于平均工资的员工有哪些？
-- 分析：1.先查询计算出平均工资  2.再查询比平均工资低的员工
select avg(salary) from emp;

select * from emp where salary<(select avg(salary) from emp);

-- 5.2:返回单列多行
-- 1：查询工资大于5000的员工，来自于哪些部门的名字
-- 分析：1.先查询工资大于5000的员工所属部门编号  2.根据编号查询对应的部门名称
select dept_id from emp where salary>5000;

select * from dept where id in (select dept_id from emp where salary>5000);
select * from dept where id in (1,2);

-- 2：查询开发部与财务部所有的员工信息
-- 分析：1.查询开发部和财务部的部门编号  2. 再根据部门编号查询员工信息
select id from dept where name='开发部' or name='财务部';

select * from emp where dept_id in (select id from dept where name='开发部' or name='财务部');

-- 5.3:返回多列多行
-- 1：查询出2011年以后入职的员工信息，包括部门名称
-- 分析：1.查询所有的员工信息及所属部门名称  2.然后再根据入职日期进行筛选
-- e.*:表示emp员工表中的所有字段
select e.*,d.name AS dept_name from emp e left join dept d on e.dept_id = d.id;

select * from (select e.*,d.name AS dept_name from emp e left join dept d on e.dept_id = d.id) AS employee where join_date>='2011-1-1';

-- 这里可以不使用子查询
select e.*,d.name AS dept_name from emp e left join dept d on e.dept_id = d.id  where join_date>='2011-1-1';

-- 6.事务
/*
    概念：事务指的是将一条或多条SQL语句作为一个整体执行，要成功都成功，要失败都失败
        zs给ls转账100
            update：zs-100		zs：900
            机房着火了|断电了
            update：ls+100		ls：1000
   作用：保证一组操作全部成功或失败。
   提交方式：
        自动提交：MYSQL默认事务自动提交，会把每一条SQL语句都放在一个事务中，自动开启，自动提交
        手动提交：实际开发中，如果我们将多条SQL语句的执行作为一个整体执行，就需要手动开启事务 手动提交
                当都执行成功了就进行commit提交，如果有一条SQL语句执行失败了就进行回滚rollback。
        查看提交方式：select @@autocommit;
            1：开启事务自动提交  0：关闭事务自动提交【开启事务手动提交】
        修改提交方式：set @@autocommit=0|1;
    事务使用：
        1.开启事务
        2.执行操作
        3.事务提交|回滚
        注意：事务最终不管是提交还是回滚，都表示一个事务结束了。
        try{
          //1.开启事务
          //2.执行操作
            zs-100
            ls+100
          //3.1：执行成功 提交事务
            commit;
        }catch(){
            //3.2：执行失败 回滚事务
            rollback；
        }
   事务特性：ACID
    A：原子性 指的是一个事务作为一个整体 要执行都执行 要不执行都不执行
    C：一致性 事务执行前后数据的完整性保存一致
    I：隔离性 指的是多个并发事务之间，事务的执行应该互不干扰【这只是理想情况】
    D：持久性 事务一旦提交，对数据库中数据作出的改变会被永久保存下来

    隔离级别引发的问题：
        脏读：在一个事务中，读到到了另一个事务中没有提交的数据。
        不可重复读：在一个事务中，两次读取的结果不一致。
        幻读：在一个事务中，插入一条记录，在另一个事务中读取不到，但是插入相同的记录会报错或删除却可以成功。
    隔离级别：
        read uncommitted：读未提交    会出现脏读、不可重复读、幻读问题
        read committed：读已提交      可以解决脏读 但是依然会出现不可重复读、幻读问题
        repeatable read：可重复读     可以解决脏读、不可重复读 但是依然会出现幻读问题
        serializable：串行化          可以解决脏读、不可重复读、幻读问题  但是效率低下
    查看隔离级别：SELECT @@TX_ISOLATION;
    设置隔离级别：SET SESSION TRANSACTION ISOLATION LEVEL 级别字符串;


*/
-- 账户表
create table account(
                        id int primary key auto_increment,
                        name varchar(20),
                        money double
);
insert into account values (null,'zs',1000);
insert into account values (null,'ls',1000);
insert into account values (null,'ww',1000);

-- 6.1:自动提交 zs给ls转账100
update account set money=money-100 where name='zs';
-- 转账过程中发送故障 下面这条SQL语句不执行
update account set money=money+100 where name='ls';

-- 查看提交方式
select @@autocommit;

-- 回滚
rollback;

-- 6.2:手动提交 zs给ls转账100
-- 查看提交方式
select @@autocommit;
-- 修改事务提交方式
set @@autocommit=0;

update account set money=money-100 where name='zs';
-- 转账过程中发送故障 下面这条SQL语句不执行
update account set money=money+100 where name='ls';

-- 回滚
rollback;
-- 提交
commit;

-- 设置MySQL数据库 事务默认提交方式为自动提交
set @@autocommit=1;

-- 6.3：事务使用
-- 1.开启事务
start transaction ;
-- 2.执行操作
update account set money=money-100 where name='zs';

-- 转账过程中发送故障   人为制造
insert into account values (2,'zhaoliu',100);

update account set money=money+100 where name='ls';
-- 3.事务提交|回滚
-- 都执行成功，提交事务
commit;
-- 如果执行有错误。回滚事务
rollback;

-- 6.4：回滚点
-- 1.开启事务
start transaction ;
-- 2.执行操作
update account set money=money-100 where name='zs';
update account set money=money-100 where name='zs';
update account set money=money-100 where name='zs';
-- 设置回滚点
savepoint a1;

update account set money=money-100 where name='zs';
update account set money=money-100 where name='zs';
update account set money=money-100 where name='zs';
update account set money=money-100 where name='zs';

savepoint a2;

update account set money=money-100 where name='zs';

-- 3.事务提交|回滚
-- 回到回滚点
rollback to a2;
-- 事务提交
commit;

-- 7：数据库设计三大范式
/*
    1NF：要求表中的每一列不可以继续拆分，具有原子性。
    2NF：要求一张表只描述一件事情
    3NF：要求表中除了主键列之外的每一列都与主键列具有直接依赖关系。
    注意：
        实际开发中，数据库表设计时一般都会遵循一二范式，第三范式根据具体情况决定。
        student： 1 zs 汉族
        --> student：1 zs 1      民族表：1 汉族
        由于多表查询的效率要远低于单表查询，所以显得表设计繁琐且效率低下。
 */

-- 数据库设计
use day1504;

create table t_user(
                       u_id int primary key auto_increment,
                       username varchar(20) not null ,
                       password varchar(20) not null ,
                       create_user varchar(36) not null,						-- 创建人
                       create_time timestamp not null default current_timestamp,			-- 创建时间
                       update_user varchar(36),					-- 更新人
                       update_time timestamp not null default current_timestamp on update current_timestamp, -- 更新时间
                       remark varchar(255),							-- 备注
                       del_flag tinyint(1) not null default 0       -- 0表示存在，1表示已删除		-- 是否删除
);





