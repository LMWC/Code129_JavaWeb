create database day25;
use day25;

drop table if exists tb_user;

create table tb_user(
                        id int primary key auto_increment,
                        username varchar(20),
                        password varchar(20),
                        gender char(1),
                        addr varchar(30)
);

INSERT INTO tb_user VALUES (1, 'zhangsan', '123', '男', '北京');
INSERT INTO tb_user VALUES (2, '李四', '234', '女', '天津');
INSERT INTO tb_user VALUES (3, '王五', '11', '男', '西安');

-- 商品表
CREATE TABLE product(
                        id INT PRIMARY KEY AUTO_INCREMENT,   -- 商品id
                        NAME VARCHAR(30),                    -- 商品名称
                        price double(10,2)
);
-- 添加数据
INSERT INTO product VALUES (1,'华为手机',13999.99);
INSERT INTO product VALUES (2,'小米手机',16789.88);
INSERT INTO product VALUES (3,'联想电脑',25899.88);
INSERT INTO product VALUES (4,'苹果电脑',29999.99);
INSERT INTO product VALUES (5,'中华香烟',3100.00);
INSERT INTO product VALUES (6,'玉溪香烟',338.00);