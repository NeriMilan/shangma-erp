package com.shangma.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.goods.FreeGoods;
import com.shangma.entity.goods.Goods;
import com.shangma.entity.goods.GoodsCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreeGoodsMapper extends BaseMapper<FreeGoods> {
    /**
     * 多表分页查询
     */
    List<Goods> getFreeGoodsList(FreeGoods freeGoods);
    /**
     * 赠品转换商品
     */
//    int insertGoodsCheck(GoodsCheck goodsCheck);
//
//    int freeToGoodsState (@Param("id") Long id,
//                          @Param("addStocks") Integer addStocks);
//
    int freeToGoods(@Param("id") Long id,
                    @Param("addStocks") Integer addStocks,
                    @Param("shopPrice") Double shopPrice);




    /**
     * 商品转赠品
     */
    int goodsToFree(@Param("id") Long id,
                    @Param("addStocks") Integer addStocks);

}