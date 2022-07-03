-- 使用db14数据库
USE day26;

-- 创建student表
CREATE TABLE student(
                        id INT PRIMARY KEY AUTO_INCREMENT,	-- 学生id
                        NAME VARCHAR(20),					-- 学生姓名
                        age INT,							-- 学生年龄
                        gender tinyint                       -- 学生性别
);

-- 添加数据
INSERT INTO student VALUES (NULL,'张三',23,1),(NULL,'李四',24,0),(NULL,'王五',25,0),(NULL,'赵六',26,1),(NULL,'小黑',27,0),(NULL,'小智',28,1);