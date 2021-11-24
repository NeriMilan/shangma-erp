package com.shangma.mapper.goodsMapper;

import com.shangma.entity.goodsEntity.ActivityMarket;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface ActivityMarketMapper extends MyMapper<ActivityMarket> {

    List<ActivityMarket> selectList();

    List<ActivityMarket> search(ActivityMarket activityMarket);



}
