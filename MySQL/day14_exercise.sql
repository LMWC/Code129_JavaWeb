-- -------------------------------------------------------------------------
-- 数据库操作sql练习
-- 一、数据库的创建：
-- 	1、创建一个名称为mydb1的数据库
create database mydb1;
-- 	2、创建一个使用utf8字符集的mydb2数据库。
create database mydb2 character set utf8;

-- 二、数据库的修改：
-- 	修改mydb2字符集为gbk;
alter database mydb2 character set gbk;

-- 三、数据库的删除：
-- 	删除数据库mydb3。
drop database if exists mydb3;

-- 四、数据库查看：
-- 	查看所有数据库。
show databases ;
-- 	查看数据库mydb1的字符集
show create database mydb1;
-- -----------------------------------------------
-- 数据库中表操作的sql练习
-- 一、创建表
-- 	1、创建一张员工表employee
-- 			字段		类型
-- 			id			整形
-- 			name		字符型
-- 			gender		字符型
-- 			birthday    日期型
-- 			entry_date  日期型
-- 			job			字符型
-- 			salary		小数型
-- 			resume		文本
-- 	2、创建一张员工表employee2
-- 			字段		类型
-- 			id			整形
-- 			name		字符型
-- 			gender		字符型
-- 			birthday    日期型
-- 			entry_date  日期型
-- 			job			字符型
-- 			salary		小数型
-- 			resume		文本
-- 	要求：把id 设置成主键，并且自动增长。name不允许为空。
use mydb1;
select database();
create table employee(
                         id int primary key auto_increment,
                         name varchar(10) not null,
                         gender varchar(10),
                         birthday date,
                         entry_date date,
                         job varchar(10),
                         salary double,
                         resume text
)
create table employee2(
                          id int primary key auto_increment,
                          name varchar(10) not null,
                          gender varchar(10),
                          birthday date,
                          entry_date date,
                          job varchar(10),
                          salary double,
                          resume text
)

-- 二、删除表
-- 	1、删除employee2表
drop table employee2;
-- 三、数据表的结构的修改：
-- 	1、在上面员工表的基本上增加一个image列。
alter table employee add image varchar(10);
-- 	2、修改job列，使其长度为60。
alter table employee modify job varchar(60);
-- 	3、删除gender列。
alter table employee drop gender;
-- 	4、表名改为user。
alter table employee rename to user;
-- 	5、修改表的字符集为utf8
alter table user character set utf8;
-- 	6、列名name修改为username
alter table user change name username varchar(10) not null ;
-- 四、查看表结构
-- 	1、查看数据库内的所有表
show tables ;
-- 	2、查看employee的建表语句
show table status from mydb1 like 'user';
show create table user;/*答案*/
-- 	3、查看employee的表结构
desc user;
-- ----------------------------------------------------
-- 表记录的操作
-- 一、插入语句 ---insert
--     1、向employee中插入三个员工信息，要求员工姓名分别是zs,ls,wangwu
insert into user(username) values ('zs');
insert into user(username) values ('ls');
insert into user(username) values ('wangwu');
-- 二、更新语句 ---update
--    1、将所有员工薪水修改为5000元。
update user set salary=5000;
--     2、将姓名为’zs’的员工薪水修改为3000元。
update user set salary=3000 where username='zs';
--     3、将姓名为’ls’的员工薪水修改为4000元,job改为ccc。
update user set salary=4000,job='ccc' where username='ls';
--     4、将wangwu的薪水在原有基础上增加1000元。
update user set salary=salary+1000 where username='wangwu';

-- 三、删除语句 ---delete
--     1、删除表中名称为’zs’的记录。
delete from user where username='zs';
--    2、删除表中所有记录。
delete from user;
-- 四、查询语句 ---select
create table exam(
                     id int primary key auto_increment,
                     name varchar(20) not null,
                     chinese double,
                     math double,
                     english double
);
insert into exam values(null,'关羽',85,76,70);
insert into exam values(null,'张飞',70,75,70);
insert into exam values(null,'赵云',90,65,95);
insert into exam values(null,'刘备',97,50,50);
insert into exam values(null,'曹操',90,89,80);
insert into exam values(null,'司马懿',90,67,65);
-- 练习：
-- 	1、查询表中所有学生的信息。
select * from exam;
-- 	2、查询表中所有学生的姓名和对应的英语成绩。
select name,english from exam;
-- 	3、过滤表中重复数据。--- 不写

-- 	4、在所有学生分数上加10分特长分。 --- 不写

-- 	5、统计每个学生的总分。
select name,(chinese+math+english) as '总分' from exam;
select *,chinese+math+english from exam;/*答案*/
-- 	6、使用别名表示学生分数。
select name,chinese as '语文',math as '数学',english as '英语' from exam;
select *,chinese+math+english as 总分 from exam;/*答案*/
-- ----------------使用where子句
-- 	7、查询姓名为刘备的学生成绩
select * from exam where name='刘备';
-- 	8、查询英语成绩大于90分的同学
select * from exam where english>90;
-- 	9、查询总分大于200分的所有同学
select * from exam where (chinese+math+english)>200;
select * from exam where chinese+math+english>200;/*答案*/
-- 	10、查询英语分数在 80－90之间的同学。
select * from exam where english between 80 and 90;
-- 	11、查询数学分数为89,75,91的同学。
select * from exam where math in (89,75,91);
-- 	12、查询所有姓刘的学生成绩。
select * from exam where name like '刘%';
-- 	13、查询所有姓刘两个字的学生成绩。
select * from exam where name like '刘_';
-- 	14、查询数学分>80并且语文分>80的同学。
select * from exam where math>80 and chinese>80;
-- 	15、查询数学分>80 或者 语文分>80的同学。
select * from exam where math>80 or chinese>80;
-- -----------------------使用order by  排序
-- 	16、对数学成绩排序后输出。
select * from exam order by math;
-- 	17、对总分排序按从高到低的顺序输出
select * from exam order by (chinese+math+english) desc ;
select *,chinese+math+english as 总分 from exam order by 总分 desc;/*答案*/
select *,chinese+math+english as 总分 from exam order by chinese+math+english desc;/*答案*/
-- 	18、对姓刘的学生成绩排序输出
select * from exam where name like '刘%' order by (chinese+math+english);
select * from exam where name like '刘%' order by math desc;/*答案*/
-- --------------------使用count（函数）
-- 	19、统计一个班级共有多少学生？
select count(name) from exam;
-- 	20、统计数学成绩大于或等于90的学生有多少个？
select count(name) from exam where math>=90;
-- 	21、统计总分大于250的人数有多少？
select count(name) from exam where (chinese+math+english)>250;
select count(id) from exam where chinese+math+english>250;/*答案*/
-- ------------------使用sum函数
-- 	22、统计一个班级数学总成绩？
select sum(math) from exam;
-- 	23、统计一个班级语文、英语、数学各科的总成绩
select sum(chinese),sum(english),sum(math) from exam;
-- 	24、统计一个班级语文、英语、数学的成绩总和
select sum(chinese)+sum(english)+sum(math) from exam;
-- 	25、统计一个班级语文成绩平均分
select sum(chinese)/count(name) from exam;
-- -----------------使用avg函数
-- 	26、求一个班级数学平均分？
select avg(math) from exam;
-- 	27、求一个班级总分平均分
select avg(chinese+math+english) from exam;
select avg(ifnull(chinese,0))+avg(ifnull(math,0))+avg(ifnull(english,0)) from exam;/*答案*/

-- --------------------使用max，min函数
-- 	28、求班级最高分和最低分（数值范围在统计中特别有用）
select max(chinese+math+english) from exam;
select max(ifnull(chinese,0)+ifnull(math,0)+ifnull(english,0)) from exam;/*答案*/
select min(chinese+math+english) from exam;
select min(ifnull(chinese,0)+ifnull(math,0)+ifnull(english,0)) from exam;/*答案*/

-- -----------------------------------------------
create table orders(
                       id int,
                       product varchar(20),
                       price float
);

insert into orders(id,product,price) values(1,'电视',900);
insert into orders(id,product,price) values(2,'洗衣机',100);
insert into orders(id,product,price) values(3,'洗衣粉',90);
insert into orders(id,product,price) values(4,'桔子',9);
insert into orders(id,product,price) values(5,'洗衣粉',90);

-- 29、查询购买了几类商品，并且每类总价大于100的商品

select product,sum(price) from orders group by product having sum(price)>100 ;
















