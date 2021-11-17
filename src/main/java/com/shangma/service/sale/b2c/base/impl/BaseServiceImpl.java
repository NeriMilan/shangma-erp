package com.shangma.service.sale.b2c.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangma.mapper.base.MyMapper;
import com.shangma.service.sale.b2c.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @CreateTime=17:18
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private MyMapper<T> myMapper;
    @Override
    public List<T> findAll() {
        return myMapper.selectList(null);
    }

    @Override
    public T findById(long id) {
        return myMapper.selectById(id);
    }

    @Override
    public List<T> search(QueryWrapper<T> queryWrapper) {
        return myMapper.selectList(queryWrapper);
    }

    @Override
    public Integer insert(T t) {
        return myMapper.insert(t);
    }

    @Override
    public Integer update(T t) {
        return myMapper.updateById(t);
    }

    @Override
    public Integer delete(long id) {
        return myMapper.deleteById(id);
    }
}
