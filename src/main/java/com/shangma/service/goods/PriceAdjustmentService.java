package com.shangma.service.goods;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.PriceAdjustment;
import com.shangma.service.goods.base.BaseService;

public interface PriceAdjustmentService extends BaseService<PriceAdjustment> {

    PageBean<PriceAdjustment> list(Integer pageNum,Integer pageSize);

    PageBean<PriceAdjustment> search(PriceAdjustment priceAdjustment,Integer pageNum,Integer pageSize);

    //查询最后一条数据的信息
    Long lastId();
}
