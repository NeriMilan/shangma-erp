package com.shangma.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.goods.GoodsCheck;

public interface GoodsCheckMapper extends BaseMapper<GoodsCheck> {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsCheck record);

    int insertSelective(GoodsCheck record);

    GoodsCheck selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCheck record);

    int updateByPrimaryKey(GoodsCheck record);
}