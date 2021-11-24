package com.shangma.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.ActivityGoods;
import com.shangma.mapper.goods.ActivityGoodsMapper;
import com.shangma.service.goods.ActivityGoodsService;
import com.shangma.service.goods.WinningRecordService;
import com.shangma.service.goods.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityGoodsServiceImpl extends BaseServiceImpl<ActivityGoods> implements ActivityGoodsService {
    @Autowired
    private ActivityGoodsMapper mapper;
    @Autowired
    private WinningRecordService winningRecordService;
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
    
    
    public int add(ActivityGoods activityGoods) {
        return mapper.insert(activityGoods);
    }
    
    /**
     * 查询最后一条id
     */
    public Long lastId() {
        return mapper.lastId();
    }
    
    /**
     * 修改
     */
    public int update(ActivityGoods activityGoods) {
        return mapper.update(activityGoods);
    }
    
    
}
