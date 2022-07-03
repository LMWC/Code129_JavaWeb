package com.itheima.dao;

import com.itheima.bean.Brand;
import com.itheima.bean.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandDao {

    /**
     * 查询所有品牌信息
     */
    List<Brand> selectAll();

    /**
     * 根据id查询品牌详细信息
     * @param id 品牌id
     * @return 品牌对象
     */
    Brand selectById(Integer id);

    /**
     * 增加品牌
     * @param brand 要增强的品牌信息 封装到Brand对象中
     * @return 返回受影响的行数 int类型  当然也可以用void 不接收返回的受影响行数
     */
    int add(Brand brand);

    /**
     * 方式一：返回增加数据的主键
     * @param brand
     * @return
     */
    int add01(Brand brand);

    /**
     * 方式二：返回增加数据的主键
     * @param brand
     * @return
     */
    int add02(Brand brand);

    /**
     * 修改全部字段
     * @param brand
     * @return
     */
    int update(Brand brand);

    /**
     * 根据id删除单个品牌
     * @param id
     * @return
     */
    int deleteById(Integer id);


    /**
     *根据品牌名称模糊查询品牌列表
     * @param brandName
     * @return
     */
    List<Brand> selectByBrandName01(String brandName);

    List<Brand> selectByBrandName02(String brandName);


    /**
     * 根据条件查询品牌信息
     * @return
     */
    List<Brand> selectByCondition01(Integer status,String companyName,String brandName);

    List<Brand> selectByCondition02(@Param("status") Integer status,@Param("companyName") String companyName,@Param("brandName") String brandName);

    List<Brand> selectByCondition03(Brand brand);


    List<Brand> selectByCondition04(Map map);


    /**
     * 根据品牌状态和品牌名称模糊查询 再根据品牌排序字段进行升序排序
     * @param queryVo
     * @return
     */
    List<Brand> selectByCondition05(QueryVo queryVo);

    /**
     * 统计品牌个数   select count(*) from tb_brand
     */
    int count();

}
