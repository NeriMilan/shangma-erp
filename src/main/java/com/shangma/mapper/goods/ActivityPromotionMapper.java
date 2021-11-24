package com.shangma.mapper.goods;

import com.shangma.entity.goods.ActivityPromotion;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface ActivityPromotionMapper extends MyMapper<ActivityPromotion> {
    //模糊查
    List<ActivityPromotion> search();

    //减去活动商品数量
    Integer reduce(Integer num, Long id);

    //查询所有
    List<ActivityPromotion> select();

    //添加活动
    int insert(ActivityPromotion activityPromotion);

    Integer update(ActivityPromotion activityPromotion);
}