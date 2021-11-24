package com.shangma.mapper.goodsMapper;

import com.shangma.entity.goodsEntity.ActivityGoods;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface ActivityGoodsMapper extends MyMapper<ActivityGoods> {
    List<ActivityGoods>search(ActivityGoods activityGoods);
}
