package com.shangma.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shangma.entity.goods.FreeGoods;
import com.shangma.entity.goods.GoodsCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/20 10:52
 */
public interface FreeGoodsService extends IService<FreeGoods> {
    /**
     * 多表分页查询
     */

    List getAll(FreeGoods freeGoods);

    /**
     * 赠品转商品
     */
//    boolean insertGoodsCheck(GoodsCheck goodsCheck);
//
//    boolean freeToGoodsState();

    @Transactional
    boolean freeToGoods(Long id, Integer addStocks, Double shopPrice);


    /**
     *商品转赠品
     */
    @Transactional
    boolean goodsToFree(Long id, Integer addStocks);
    
    /**
     * 减去赠品数量
     * @return
     */
    Long reduce(Integer num,Long id);
}
