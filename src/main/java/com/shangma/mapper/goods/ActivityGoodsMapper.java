package com.shangma.mapper.goods;

import com.shangma.entity.goods.ActivityGoods;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface ActivityGoodsMapper extends MyMapper<ActivityGoods> {
    

    List<ActivityGoods>search(ActivityGoods activityGoods);
    
    int insert(ActivityGoods activityGoods);
    
    Long lastId();
    
    int update(ActivityGoods activityGoods);
}
