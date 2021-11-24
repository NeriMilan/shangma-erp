package com.shangma.service.goods.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface BaseService<T> {

    /**
     * 查询所有
     */
    List<T> list();
    /**
     * 条件分页查询
     */
    List<T> search(QueryWrapper<T> queryWrapper);

    /**
     * 通过id查询
     */
    T findById(Long id);
    /**
     * 添加
     */
    int add(T t);
    /**
     * 修改
     */
    int update(T t);
    /**
     * 删除
     */
    int deleteById(Long id);
    /**
     * 批量删除
     */
    int batchDeleteByIds(List<Long> ids);
}

