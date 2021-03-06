package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据分类ID查询品牌集合
     * @param categoryId
     * @return
     */
    @Select("SELECT tb")
    List<Brand> findByCategory(Integer categoryId);
}
