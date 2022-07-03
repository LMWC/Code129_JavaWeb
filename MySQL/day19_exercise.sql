use day19;
drop table if exists tb_dept;
create table tb_dept(
                        id varchar(10) PRIMARY key,
                        name VARCHAR(10) not null UNIQUE
);
insert into tb_dept values('0000001','销售部'),('0000002','研发部'),('0000003','人事部');

drop table if exists tb_company;
create table tb_company(
                           id varchar(10) primary key,
                           name varchar(10) not null unique,
                           address varchar(50) not null,
                           phone varchar(11) not null,
                           industry varchar(11) not null
);
insert into tb_company values('00001','小米','北京','88888888','科技'),('00002','华为','深圳','9999999','科技'),('00003','三个松鼠','北京','777777777','食品');