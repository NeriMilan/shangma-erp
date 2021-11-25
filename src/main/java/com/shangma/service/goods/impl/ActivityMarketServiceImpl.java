package com.shangma.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.ActivityMarket;
import com.shangma.mapper.goods.FreeGoodsMapper;
import com.shangma.mapper.goods.ActivityMarketMapper;
import com.shangma.service.goods.ActivityGoodsService;
import com.shangma.service.goods.ActivityMarketService;
import com.shangma.service.goods.FreeGoodsService;
import com.shangma.service.goods.WinningRecordService;
import com.shangma.service.goods.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityMarketServiceImpl extends BaseServiceImpl<ActivityMarket> implements ActivityMarketService {
    @Autowired
    private ActivityMarketMapper marketMapper;
    //赠品表
    @Autowired
    private FreeGoodsService freeService;
    //活动赠品表
    @Autowired
    private ActivityGoodsService goodsService;
    //中奖人员表
    @Autowired
    private WinningRecordService winningRecordService;
    
    @Override
    public PageBean<ActivityMarket> list(Integer pageNumber, Integer pageSize) {
        
        PageHelper.startPage(pageNumber, pageSize);
        List<ActivityMarket> activityMarkets = marketMapper.selectList(null);
        long total = new PageInfo<>(activityMarkets).getTotal();
        return PageBean.initData(total, activityMarkets);
    }
    
    /**
     * 模糊查询市场活动
     */
    @Override
    public PageBean<ActivityMarket> search(ActivityMarket activityMarket, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ActivityMarket> search = marketMapper.search(activityMarket);
        long total = new PageInfo<>(search).getTotal();
        return PageBean.initData(total, search);
    }
    
    /**
     * 增加市场活动相关信息
     */
    @Override
    public int add(ActivityMarket market) {
        
        //向活动赠品表添加对应的数据
        goodsService.add(market.getActivityGoods());
        //获取活动赠品表的id赋值给活动表
        Long aLong = goodsService.lastId();
        System.out.println(aLong);
        market.setGId(aLong);
        
        //向活动表加入对应的字段名称
        System.out.println("==================================");
        System.out.println(market);
        int row = marketMapper.insert(market);
        
        return row;
    }
    
    /**
     * 修改未审核的市场活动信息
     */
    @Override
    public int update(ActivityMarket market) {
        //取出活动商品id，以便进行修改
        Long gId = market.getGId();
        
        market.getActivityGoods().setId(gId);
        
        int update = goodsService.update(market.getActivityGoods());
        
        int update1 = marketMapper.update(market);
        if (update > 0 && update1 > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * 审核
     */
    public int review(ActivityMarket market) {
        int row = 0;
        Long freebiesId = market.getFreebiesId();
        Integer freebiesNum = market.getFreebiesNum();
        Long gId = market.getGId();
        //向赠品表取出对应id的赠品信息然后赠品表减去freebiesNum
        freeService.reduce(freebiesNum, freebiesId);
        if (market.getApprovalState() == 1) {
            //当审核通过时再次去赠品表去查询该赠品的数量，防止数据库中赠品数量为负数
            Integer minStocks = freeService.getById(freebiesId).getMinStocks();
            
            
            if (minStocks > freebiesNum) {
                //向赠品表取出对应id的赠品信息然后赠品表减去freebiesNum
                freeService.reduce(freebiesNum, freebiesId);
                //计算赠品表的剩余数量
                int residuals = minStocks - freebiesNum;
                market.getActivityGoods().setFreeGoodsResiduals(residuals);
                market.getActivityGoods().setId(gId);
                goodsService.update(market.getActivityGoods());
                
                //修改状态
                return marketMapper.update(market);
            } else {
                //库存小于转移量，将转移所有库存
                market.setFreebiesNum(minStocks);
                freeService.reduce(minStocks, freebiesId);
            }
        } else if (market.getApprovalState() == 2) {
            return marketMapper.update(market);
        }
        return row;
    }
    
    /**
     * 添加中奖用户
     */
    public int winPeople(ActivityMarket market) {
        //向中将表添加信息
        winningRecordService.add(market.getActivityGoods().getWinningRecord());
        //获取中奖表id赋值给赠品表
        Long winId = winningRecordService.lastId();
        market.getActivityGoods().setRId(winId);
        //向活动赠品表添加对应的数据
        goodsService.add(market.getActivityGoods());
        //获取赠品表的id赋值给活动表
        Long aLong = goodsService.lastId();
        market.setGId(aLong);
        //向活动表加入对应的字段名称
        int row = marketMapper.insert(market);
        
        return row;
    }
}
