package com.shangma.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.goods.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 多表分页查询
     */
    List<Goods> getGoodsList(Goods goods);
    //减去商品的数量
    Integer reduce(Integer num,Long id);
    //加上商品的数量
    Integer plus(Integer num,Long id);
}