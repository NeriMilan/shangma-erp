package com.shangma.service.goods;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.ActivityPromotion;
import com.shangma.service.goods.base.BaseService;



public interface ActivityPromotionService extends BaseService<ActivityPromotion> {

    PageBean<ActivityPromotion> list(Integer pageSize, Integer pageNumber);

    PageBean<ActivityPromotion> search(ActivityPromotion activityPromotion, Integer pageSize, Integer pageNumber);

    int increase(ActivityPromotion activityPromotion);
    //审核
    int review(ActivityPromotion activityPromotion);
}
