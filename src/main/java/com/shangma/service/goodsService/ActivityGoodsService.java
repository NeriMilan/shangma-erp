package com.shangma.service.goodsService;

import com.shangma.common.pagebean.PageBean;

import com.shangma.entity.goodsEntity.ActivityGoods;
import com.shangma.service.goodsService.base.BaseService;

public interface ActivityGoodsService extends BaseService<ActivityGoods> {
    PageBean<ActivityGoods> list(Integer pageNumber, Integer pageSize);
    PageBean<ActivityGoods> search(ActivityGoods activityGoods,Integer pageNumber,Integer pageSize);
}
