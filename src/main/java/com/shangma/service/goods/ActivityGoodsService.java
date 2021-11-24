package com.shangma.service.goods;

import com.shangma.common.pagebean.PageBean;

import com.shangma.entity.goods.ActivityGoods;
import com.shangma.service.goods.base.BaseService;

public interface ActivityGoodsService extends BaseService<ActivityGoods> {
    PageBean<ActivityGoods> list(Integer pageNumber, Integer pageSize);
    PageBean<ActivityGoods> search(ActivityGoods activityGoods,Integer pageNumber,Integer pageSize);
    Long lastId();
    int update(ActivityGoods activityGoods);
}
