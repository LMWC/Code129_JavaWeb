package com.itheima.dao;

import com.itheima.bean.Brand;
import com.itheima.bean.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandDao {
    /**
     * 修改品牌的全部信息
     * @param brand 要修改的品牌信息
     * @return 受影响的行数
     */
    int update(Brand brand);


    /**
     * 多条件查询
     * @param brand 封装查询条件
     * @return
     */
    List<Brand> selectByCondition(Brand brand);


}
