-- 数据库建表语句
create database day20;

use day20;

-- 删除tb_brand表
drop table if exists tb_brand;
-- 创建tb_brand表
create table tb_brand
(
    -- id 主键
    id           int primary key auto_increment,
    -- 品牌名称
    brand_name   varchar(20),
    -- 企业名称
    company_name varchar(20),
    -- 排序字段
    ordered      int,
    -- 描述信息
    description  varchar(100),
    -- 状态：0：禁用  1：启用
    status       int
);
-- 添加数据
insert into tb_brand (brand_name, company_name, ordered, description, status)
values ('三只松鼠', '三只松鼠股份有限公司', 5, '好吃不上火', 0),
       ('华为', '华为技术有限公司', 100, '华为致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界', 1),
       ('小米', '小米科技有限公司', 50, 'are you ok', 1);
SELECT * FROM tb_brand;

-- 项目搭建步骤：
--     1.准备工作：创建数据库 表 插入测试数据
--     2.创建Maven工程【JavaSE】 添加依赖
--     3.创建pojo
--     4.创建dao接口
--     5.创建dao接口映射文件
--     6.创建MyBatis核心配置文件
--     7.编写测试类 运行测试