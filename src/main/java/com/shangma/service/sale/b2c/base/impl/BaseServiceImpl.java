package com.shangma.service.sale.b2c.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.mapper.base.MyMapper;
import com.shangma.service.sale.b2c.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<T> searchPages(int pageNum, int pageSize, QueryWrapper<T> queryWrapper) {
        PageHelper.startPage(pageNum,pageSize);
        List<T> list = myMapper.selectList(queryWrapper);
        PageInfo<T> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Integer insert(T t) {
        return myMapper.insert(t);
    }

    @Override
    public Integer update(T t) {
        return myMapper.updateById(t);
    }

    @Transactional
    @Override
    public Integer deleteBatchIds(List<Long> ids) {
        return  myMapper.deleteBatchIds(ids);
    }
}
