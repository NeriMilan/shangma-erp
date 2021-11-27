package com.shangma.service.goods;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.ActivityMarket;
import com.shangma.service.goods.base.BaseService;


public interface ActivityMarketService extends BaseService<ActivityMarket> {
    PageBean<ActivityMarket> list(Integer pageNumber,Integer pageSize);
    PageBean<ActivityMarket> search(ActivityMarket activityMarket,Integer pageNum,Integer pageSize);
    int review(ActivityMarket activityMarket);
    int winPeople(ActivityMarket activityMarket);
}
