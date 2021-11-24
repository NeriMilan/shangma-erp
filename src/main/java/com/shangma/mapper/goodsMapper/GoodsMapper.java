package com.shangma.mapper.goodsMapper;


import com.shangma.entity.goodsEntity.Goods;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface GoodsMapper extends MyMapper<Goods> {
    //模糊查询
    List<Goods> search(Goods goods);
    //减去商品的数量
    Integer reduce(Integer num,Long id);
    //加上商品的数量
    Integer plus(Integer num,Long id);

}