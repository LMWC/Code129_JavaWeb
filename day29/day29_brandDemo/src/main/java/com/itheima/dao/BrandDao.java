package com.itheima.dao;

import com.itheima.bean.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandDao {
    List<Brand> selectAll();

    int add(Brand brand);

    //@Delete("delete from tb_brand where id=#{id}")
    int delete(int id);

    //@Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    int update(Brand brand);

    //批量删除   因为ids是数组类型 不是简单类型 不能直接传递 所以使用@Param设置了别名
    int deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询-查询当前页数据   传递多个简单类型参数不能直接传递：解决方式：1.@Param  2.封装对象  3.封装到Map集合
     * @param offset      当前页起始数据下标   (currentPage-1)*pageSize
     * @param pageSize    每页显示条数
     * @return 当前页数据集合
     */
    //@ResultMap("brandMap")
    //@Select("select * from tb_brand limit #{offset},#{pageSize}")
    List<Brand> selectByPage(@Param("offset") int offset,@Param("pageSize") int pageSize);

    //分页查询-查询总条数
    //@Select("select count(*) from tb_brand")
    int total();

    /**
     * 条件查询--根据条件查询当前页数据
     * @param offset
     * @param pageSize
     * @return
     */
    List<Brand> selectByPageOnCondition(@Param("offset") int offset,@Param("pageSize") int pageSize,@Param("brand") Brand brand);

    //条件查询--根据条件查询总条数
    int totalOnCondition(@Param("brand") Brand brand);
}
