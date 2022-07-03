package com.itheima.dao;

import com.itheima.bean.Brand;
import com.itheima.bean.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandDao {
    List<Brand> selectAll();

    int add(Brand brand);

    int update(Brand brand);

    int delete(Integer id);

    /*
        批量删除：
            1.传递的参数不是一个简单类型 不能直接传递  需要打上@Param注解
            2.批量删除  需要使用动态SQL标签foreach遍历id数组 用在in后面
            delete from tb_brand where id in (11,12)
     */
    int deleteByIds(@Param("ids") int[] ids);

    //分页查询-查询当前页数据
    List<Brand> selectByPageList(@Param("offset") int offset,@Param("pageSize") int pageSize);

    //分页查询-查询总条数
    int selectByPageTotal();

    //条件查询-查询当前页数据
    List<Brand> selectByPageOnConditionList(PageParam pageParam);

    //条件查询-查询总条数
    int selectByPageOnConditionTotal(PageParam pageParam);
}
