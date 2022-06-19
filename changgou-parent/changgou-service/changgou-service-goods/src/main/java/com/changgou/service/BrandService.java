package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {


    /**
     * 条件搜索
     *
     * @param page  当前页
     * @param size  每页显示的条数
     * @param brand 搜索条件
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);

    /**
     * 条件搜索
     *
     * @param page 当前页
     * @param size 每页显示的条数
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * 根据品牌信息多条件查询
     */
    List<Brand> findList(Brand brand);

    /**
     * 删除品牌
     */
    void delete(Integer id);

    /**
     * 通过id修改品牌
     */
    void update(Brand brand);

    /**
     * 增加品牌
     */
    void add(Brand brand);

    /**
     * 根据ID查询
     */
    Brand findById(Integer id);

    /**
     * 查询所有
     */
    List<Brand> findAll();

}
