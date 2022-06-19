package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    //条件分页查询
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);
        //条件 name不为空 则根据名字搜索，letter不为空 则根据letter搜索
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        //封装PageInfo
        return new PageInfo<Brand>(brands);
    }

    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        //分页实现 page 当前页 size每页显示多少条
        PageHelper.startPage(page, size);
        //查询集合
        List<Brand> brands = brandMapper.selectAll();

        //封装PageInfo
        return new PageInfo<Brand>(brands);
    }

    /**
     * 多条件查询
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        //调用方法
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 条件构建
     *
     * @param
     */
    public Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (brand != null) {
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void add(Brand brand) {
        //带有Selective  会忽略空值
        brandMapper.insertSelective(brand);
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }
}
