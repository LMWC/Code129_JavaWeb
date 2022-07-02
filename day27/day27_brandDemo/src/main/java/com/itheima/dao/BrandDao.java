package com.itheima.dao;

import com.itheima.bean.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandDao {

    //查询所有
    //使用@ResultMap注解引用配置好的手动字段属性映射resultMap
    @ResultMap("brandMap")
    @Select("select * from tb_brand")
    List<Brand> selectAll();


    //新增
    @Insert("INSERT INTO tb_brand VALUES (null,#{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    int add(Brand brand);

    //查询单个
    @ResultMap("brandMap")
    @Select("select * from tb_brand where id=#{id}")
    Brand selectById(int id);


    //更新
    @Update("UPDATE tb_brand SET brand_name=#{brandName}, company_name=#{companyName}, ordered=#{ordered}, description=#{description}, status=#{status} where id=#{id}")
    int update(Brand brand);


    //删除
    @Delete("delete from tb_brand where id=#{id}")
    int delete(int id);
}
