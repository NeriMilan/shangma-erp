package com.shangma.service.goodsService;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goodsEntity.ActivityMarket;
import com.shangma.service.goodsService.base.BaseService;
import org.springframework.stereotype.Service;


public interface ActivityMarketService extends BaseService<ActivityMarket> {
    PageBean<ActivityMarket> list(Integer pageNumber,Integer pageSize);
    PageBean<ActivityMarket> search(ActivityMarket activityMarket,Integer pageNum,Integer pageSize);

}
