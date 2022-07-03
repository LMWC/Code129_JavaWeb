-- 数据库准备
create database day16;
use day16;
create table user(
                     id int primary key auto_increment,
                     username varchar(20),
                     password varchar(20),
                     nickname varchar(20)
);

INSERT INTO `USER` VALUES(null,'zs','123456','老张');
INSERT INTO `USER` VALUES(null,'ls','123456','老李');
INSERT INTO `USER` VALUES(null,'wangwu','123','东方不败');

-- 账户表
create table account(
                        id int primary key auto_increment,
                        name varchar(20),
                        money double
);
insert into account values (null,'zs',1000);
insert into account values (null,'ls',1000);
insert into account values (null,'ww',1000);