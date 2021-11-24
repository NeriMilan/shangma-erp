package com.shangma.mapper.goods;

import com.shangma.entity.goods.ActivityMarket;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface ActivityMarketMapper extends MyMapper<ActivityMarket> {

    List<ActivityMarket> selectList();

    List<ActivityMarket> search(ActivityMarket activityMarket);

    int insert(ActivityMarket activityMarket);
    int update(ActivityMarket market);
}
