package com.shangma.service.goodsService;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goodsEntity.ActivityPromotion;
import com.shangma.entity.goodsEntity.Goods;
import com.shangma.service.goodsService.base.BaseService;



public interface GoodsService extends BaseService<Goods> {
    /**
     * 查询所有数据
     * @param pageSize 每页显示的条数
     * @param pageNumber 页数
     */

    PageBean<Goods> list(Integer pageSize, Integer pageNumber);
    /**
     * 条件分页查询
     */
    PageBean<Goods> search(Goods goods, Integer pageSize, Integer pageNumber);


    //减去商品的数量
    Integer reduce(Integer num,Long id);

    //加上商品的数量
    Integer plus(Integer num,Long id);
}
