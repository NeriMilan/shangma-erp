package com.shangma.service.goodsService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goodsEntity.ActivityGoods;
import com.shangma.mapper.goodsMapper.ActivityGoodsMapper;
import com.shangma.service.goodsService.ActivityGoodsService;
import com.shangma.service.goodsService.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityGoodsServiceImpl extends BaseServiceImpl<ActivityGoods> implements ActivityGoodsService {
    @Autowired
    private ActivityGoodsMapper mapper;

    public PageBean<ActivityGoods> list(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<ActivityGoods> activityGoods = mapper.selectList(null);
        long total = new PageInfo<>(activityGoods).getTotal();
        return PageBean.initData(total, activityGoods);
    }

    /**
     * 模糊
     */
    public PageBean<ActivityGoods> search(ActivityGoods activityGoods, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<ActivityGoods> search = mapper.search(activityGoods);
        long total = new PageInfo<>(search).getTotal();
        return PageBean.initData(total, search);
    }
}
