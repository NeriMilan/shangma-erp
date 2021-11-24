package com.shangma.service.sale.b2c.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;

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
    Integer insert(T t);
    Integer update(T t);
    Integer deleteBatchIds(List<Long> ids);
    List<T> search(QueryWrapper<T> queryWrapper);
    PageInfo<T> searchPages(int pageNum, int pageSize, LambdaQueryWrapper<T> queryWrapper);
}
