CREATE DATABASE day19;
USE day19;
CREATE TABLE t_user(
                       uid int PRIMARY KEY auto_increment,
                       username varchar(40),
                       sex varchar(10),
                       birthday date,
                       address varchar(40)
);
/* 表名两边加的是撇 不是单引号 作用是为了取消某些关键字的特殊含义 相当于转义字符 可以加也可以不加 撇在键盘的左上角tab键上面*/
INSERT INTO `t_user` VALUES (null, 'zs', '男', '2018-08-08', '北京');
INSERT INTO `t_user` VALUES (null, 'ls', '女', '2018-08-30', '武汉');
INSERT INTO `t_user` VALUES (null, 'ww', '男', '2018-08-08', '北京');