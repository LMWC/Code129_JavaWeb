package com.itheima.dao;

import com.itheima.bean.Brand;
import com.itheima.bean.QueryVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BrandDao {

    /**
     * 动态SQL实现多条件查询 根据条件查询品牌信息 if + where 1=1
     * @return
     */
    List<Brand> selectByCondition01(Brand brand);

    /**
     * 动态SQL实现多条件查询 根据条件查询品牌信息 if + where
     * @return
     */
    List<Brand> selectByCondition02(Brand brand);

    /**
     * 动态SQL实现多条件查询 根据条件查询品牌信息 if + trim
     * @return
     */
    List<Brand> selectByCondition03(Brand brand);

    /**
     * 动态SQL实现单条件查询 根据条件查询品牌信息 choose
     */
    List<Brand> selectByCondition04(Brand brand);


    /**
     * 动态修改字段 if
     */
    int update01(Brand brand);

    /**
     * 动态修改字段 if+set
     */
    int update02(Brand brand);

    /**
     * 动态修改字段 if+trim
     */
    int update03(Brand brand);

    /**
     * 批量删除
     */
    int deleteByIds(@Param("ids") int[] ids);

    /*=================================================注解实现简单的增删改查================================================*/
    /*
        实现步骤：
            1.在dao接口中定义方法
            2.在方法上打上对应的注解  设置SQL语句
                增加：@Insert("增加SQL语句")
                删除：@Delete("删除SQL语句")
                修改：@Update("修改SQL语句")
                查询：@Select("查询SQL语句")
                引用resultMap：@ResultMap("resultMap标签的id属性值")

           注意：
                1.简单操作使用注解实现  复杂操作建议使用映射文件配置SQL语句  【企业实际开发，还是使用xml映射文件配置SQL语句居多】
                2.注解和xml映射文件可以同时使用
    */

    //增加品牌
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    int add(Brand brand);

    //修改品牌
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    int update(Brand brand);

    //根据id删除品牌
    @Delete("delete from tb_brand where id=#{id}")
    int delete(Integer id);

    //根据di查询
    @ResultMap("brandMap")
    @Select("select * from tb_brand where id=#{id}")
    Brand selectById(Integer id);

    //查询所有品牌
    @ResultMap("brandMap")
    @Select("select * from tb_brand")
    List<Brand> selectAll();
}
