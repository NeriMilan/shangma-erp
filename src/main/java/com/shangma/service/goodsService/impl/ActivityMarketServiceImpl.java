package com.shangma.service.goodsService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goodsEntity.ActivityMarket;
import com.shangma.mapper.goodsMapper.ActivityMarketMapper;
import com.shangma.service.goodsService.ActivityMarketService;
import com.shangma.service.goodsService.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityMarketServiceImpl extends BaseServiceImpl<ActivityMarket> implements ActivityMarketService {
@Autowired
private ActivityMarketMapper marketMapper;

    @Override
    public PageBean<ActivityMarket> list(Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        List<ActivityMarket> activityMarkets = marketMapper.selectList(null);
        long total = new PageInfo<>(activityMarkets).getTotal();
        return PageBean.initData(total,activityMarkets);
    }

    /**
     *模糊查询市场活动
     */
    @Override
    public PageBean<ActivityMarket> search(ActivityMarket activityMarket, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ActivityMarket> search = marketMapper.search(activityMarket);
        long total = new PageInfo<>(search).getTotal();
          return PageBean.initData(total,search);
    }

    @Override
    public int add(ActivityMarket market){
        Long freebiesId = market.getFreebiesId();
        Integer freebiesNum = market.getFreebiesNum();
        //向赠品表取出对应id的赠品信息然后赠品表减去freebiesNum

        //向活动赠品表添加对应的数据

        //向活动表加入对应的字段名称
        int insert = marketMapper.insert(market);
        return insert;
    }

}
