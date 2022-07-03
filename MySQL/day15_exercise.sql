-- 新建一个day09_exercise的数据库
create database day09_exercise;
use day09_exercise;

-- 用户表(user)
create table `user` (
                        `id` int auto_increment primary key,
                        `username` varchar(50)  -- 用户姓名
);

-- 订单表(orders)
create table `orders` (
                          `id` int  auto_increment primary key,
                          `price` double,
                          `user_id` int
);
-- 给订单表添加外键约束
alter table orders add constraint fk_user_id foreign key (user_id) references user(id);

-- 向user表中添加数据
insert into user values(3,'张三');
insert into user values(4,'李四');
insert into user values(5,'王五');
insert into user values(6,'赵六');

-- 向orders 表中插入数据
insert into orders values(1,1314,3);
insert into orders values(2,1314,3);
insert into orders values(3,15,4);
insert into orders values(4,315,5);
insert into orders values(5,1014,null);


-- 练习:
-- 查询用户的订单,没有订单的用户不显示
select orders.id,orders.price,user.id,user.username from user,orders where user.id=orders.user_id;
select *from user,orders where user.id=orders.user_id;/*答案*/
select * from user join orders on user.id=orders.user_id;/*答案*/
-- 查询所有用户的订单详情
select orders.id,orders.price from user,orders where user.id=orders.user_id;
select *from user left join orders on user.id=orders.user_id;/*答案*/
-- 查询所有订单的用户详情
select user.id,user.username from user,orders where user.id=orders.user_id;
select *from user right join orders on user.id=orders.user_id;/*答案*/


-- 练习:
-- 查看用户为张三的订单详情
select * from orders where user_id=(select id from user where username='张三');
select * from orders where user_id = (select id from user where username = "张三");/*答案*/
-- 查询出订单的价格大于300的所有用户信息。
select * from user where id in (select user_id from orders where price>300);
select * from user where id in (select user_id from orders where price>300);/*答案*/
-- 查询订单价格大于300的订单信息及相关用户的信息。
-- select * from (select e.*,d.* from orders e left join user d on e.user_id = d.id) as orders where price>300;
SELECT *FROM USER,orders WHERE user.id=orders.user_id AND orders.price > 300;/*答案*/



-- 扩展练习-----------------------------------------------------------------------------
-- 部门表
CREATE TABLE dept (
                      id INT PRIMARY KEY PRIMARY KEY, -- 部门id
                      dname VARCHAR(50), -- 部门名称
                      loc VARCHAR(50) -- 部门位置
);

-- 添加4个部门
INSERT INTO dept(id,dname,loc) VALUES
(10,'教研部','北京'),
(20,'学工部','上海'),
(30,'销售部','广州'),
(40,'财务部','深圳');

-- 职务表，职务名称，职务描述
CREATE TABLE job (
                     id INT PRIMARY KEY,
                     jname VARCHAR(20),
                     description VARCHAR(50)
);

-- 添加4个职务
INSERT INTO job (id, jname, description) VALUES
(1, '董事长', '管理整个公司，接单'),
(2, '经理', '管理部门员工'),
(3, '销售员', '向客人推销产品'),
(4, '文员', '使用办公软件');

-- 员工表
CREATE TABLE emp (
                     id INT PRIMARY KEY, -- 员工id
                     ename VARCHAR(50), -- 员工姓名
                     job_id INT, -- 职务id
                     mgr INT , -- 上级领导
                     joindate DATE, -- 入职日期
                     salary DECIMAL(7,2), -- 工资
                     bonus DECIMAL(7,2), -- 奖金
                     dept_id INT, -- 所在部门编号
                     CONSTRAINT emp_jobid_ref_job_id_fk FOREIGN KEY (job_id) REFERENCES job (id),
                     CONSTRAINT emp_deptid_ref_dept_id_fk FOREIGN KEY (dept_id) REFERENCES dept (id)
);

-- 添加员工
INSERT INTO emp(id,ename,job_id,mgr,joindate,salary,bonus,dept_id) VALUES
(1001,'孙悟空',4,1004,'2000-12-17','8000.00',NULL,20),
(1002,'卢俊义',3,1006,'2001-02-20','16000.00','3000.00',30),
(1003,'林冲',3,1006,'2001-02-22','12500.00','5000.00',30),
(1004,'唐僧',2,1009,'2001-04-02','29750.00',NULL,20),
(1005,'李逵',4,1006,'2001-09-28','12500.00','14000.00',30),
(1006,'宋江',2,1009,'2001-05-01','28500.00',NULL,30),
(1007,'刘备',2,1009,'2001-09-01','24500.00',NULL,10),
(1008,'猪八戒',4,1004,'2007-04-19','30000.00',NULL,20),
(1009,'罗贯中',1,NULL,'2001-11-17','50000.00',NULL,10),
(1010,'吴用',3,1006,'2001-09-08','15000.00','0.00',30),
(1011,'沙僧',4,1004,'2007-05-23','11000.00',NULL,20),
(1012,'李逵',4,1006,'2001-12-03','9500.00',NULL,30),
(1013,'小白龙',4,1004,'2001-12-03','30000.00',NULL,20),
(1014,'关羽',4,1007,'2002-01-23','13000.00',NULL,10);

-- 工资等级表
CREATE TABLE salarygrade (
                             grade INT PRIMARY KEY,
                             losalary INT,
                             hisalary INT
);

-- 添加5个工资等级
INSERT INTO salarygrade(grade,losalary,hisalary) VALUES
(1,7000,12000),
(2,12010,14000),
(3,14010,20000),
(4,20010,30000),
(5,30010,99990);

-- --------------------------------------------------------------------------------------------------------
-- 1.查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述
SELECT e.`id`, e.`ename`, e.`salary`, j.`jname`, j.`description` FROM emp e INNER JOIN job j ON e.job_id=j.id;
-- 2.查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述，部门名称，部门位置
SELECT e.`id`, e.`ename`, e.`salary`, j.`jname`, j.`description`, d.`dname`, d.`loc` FROM emp e INNER JOIN job j INNER JOIN dept d ON e.job_id=j.id AND e.dept_id=d.id;
-- 3.查询所有员工信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级
SELECT e.`ename`, e.`salary`, j.`jname`, j.`description`, d.`dname`, d.`loc`, s.`grade` FROM emp e INNER JOIN job j INNER JOIN dept d INNER JOIN salarygrade s ON e.job_id=j.id AND e.dept_id=d.id AND e.salary BETWEEN s.losalary AND hisalary;
-- 4.查询经理的信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级
SELECT e.`ename`, e.`salary`, j.`jname`, j.`description`, d.`dname`, d.`loc`, s.`grade` FROM emp e INNER JOIN job j INNER JOIN dept d INNER JOIN salarygrade s ON e.job_id=j.id AND e.dept_id=d.id AND e.salary BETWEEN s.losalary AND hisalary AND j.jname='经理';
-- 5.查询出部门编号、部门名称、部门位置、部门人数
SELECT d.`id`, d.dname, d.`loc`, e.total 部门人数 FROM dept d INNER JOIN (SELECT dept_id, COUNT(*) total FROM emp GROUP BY dept_id) e ON e.dept_id=d.`id`;
-- 6.查询所有员工信息。显示员工信息和部门名称，没有员工的部门也要显示
SELECT e.*, d.`dname` FROM emp e RIGHT JOIN dept d ON e.dept_id=d.id;
-- 7.查询所有员工信息。显示员工姓名，员工工资，职务名称，工资等级，并按工资升序排序
SELECT e.`ename`, j.`jname`, e.`salary`, s.`grade` FROM emp e INNER JOIN job j INNER JOIN salarygrade s ON e.job_id=j.id AND e.salary BETWEEN s.losalary AND s.hisalary ORDER BY e.`salary`;
-- 8.列出所有员工的姓名及其直接上级的姓名,没有领导的员工也需要显示
SELECT e.`ename`, IFNULL(m.`ename`, '没有') 上司 FROM emp e LEFT JOIN emp m ON e.`mgr`=m.`id`;
-- 9.查询入职期早于直接上级的所有员工编号、姓名、部门名称
SELECT e.`id`, e.`ename`, d.`dname` FROM emp e INNER JOIN emp m INNER JOIN dept d ON e.mgr=m.id AND e.joindate<m.joindate AND e.`dept_id`=d.`id`;
-- 10.查询工资高于公司平均工资的所有员工信息。显示员工信息，部门名称，上级领导，工资等级
SELECT e.*, d.`dname`, m.`ename`, s.`grade` FROM emp e INNER JOIN emp m INNER JOIN dept d INNER JOIN salarygrade s WHERE e.dept_id=d.id AND e.mgr=m.id AND e.salary BETWEEN s.losalary AND hisalary AND e.salary>(SELECT AVG(salary) FROM emp);
