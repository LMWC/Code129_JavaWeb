-- 删除db14数据库
drop database if exists db14;

-- 创建db14数据库
CREATE DATABASE db14;

-- 使用db14数据库
USE db14;

-- 创建student表
CREATE TABLE student(
                        id INT PRIMARY KEY AUTO_INCREMENT,	-- 学生id
                        NAME VARCHAR(20),					-- 学生姓名
                        age INT							-- 学生年龄
);

-- 添加数据
INSERT INTO student VALUES (NULL,'张三',23),(NULL,'李四',24),(NULL,'王五',25),(NULL,'赵六',26),(NULL,'小黑',27),(NULL,'小智',28);