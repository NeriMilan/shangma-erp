package com.shangma.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

public interface MyMapper<T> extends BaseMapper<T> {
    int updateByid(Serializable id);
}
