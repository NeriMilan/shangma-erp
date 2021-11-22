package com.shangma.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.goods.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 多表分页查询
     */
    List<Goods> getGoodsList(@Param("goodsName") String goodsName,
                             @Param("goodsModel") String goodsModel,
                             @Param("goodsColor") String goodsColor,
                             @Param("brandId") Long brandId,
                             @Param("typeId") Long typeId);
}