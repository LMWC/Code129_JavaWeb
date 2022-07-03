-- 创建数据库
CREATE DATABASE day27;
-- 使用数据库
USE day27;


-- 删除tb_brand表
DROP TABLE IF EXISTS tb_brand;
-- 创建tb_brand表
CREATE TABLE tb_brand
(
    -- id 主键
    id           INT PRIMARY KEY AUTO_INCREMENT,
    -- 品牌名称
    brand_name   VARCHAR(20),
    -- 企业名称
    company_name VARCHAR(20),
    -- 排序字段
    ordered      INT,
    -- 描述信息
    description  VARCHAR(100),
    -- 状态：0：禁用  1：启用
    STATUS       INT
);
-- 添加数据
INSERT INTO tb_brand (brand_name, company_name, ordered, description, STATUS)
VALUES ('三只松鼠', '三只松鼠股份有限公司', 5, '好吃不上火', 0),
       ('华为', '华为技术有限公司', 100, '华为致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界', 1),
       ('小米', '小米科技有限公司', 50, 'are you ok', 1);


SELECT * FROM tb_brand;


-- 创建用户表
CREATE TABLE tb_user(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(20) UNIQUE,
                        password VARCHAR(32)
);

-- 添加数据
INSERT INTO tb_user(username,password) VALUES('zhangsan','123'),('lisi','234');

SELECT * FROM tb_user;


