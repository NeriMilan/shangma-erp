package com.shangma.service.sale.b2c.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * @CreateTime=17:14
 */
public interface BaseService<T> {
    /**
     * 基类中有增删改查四个功能
     * 条件分页查询
     */
    List<T> findAll();
    T findById(long id);
    List<T> search(QueryWrapper<T> queryWrapper);
    Integer insert(T t);
    Integer update(T t);
    Integer delete(long id);
}
