package com.changgou.dao;

import com.changgou.goods.pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

//Dao接口继承tk下的Mapper
//继承之后就可以直接调用方法 不用写SQL语句
public interface BrandMapper extends Mapper<Brand> {

}
