package com.shangma.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.ActivityPromotion;
import com.shangma.mapper.goods.ActivityPromotionMapper;
import com.shangma.service.goods.ActivityPromotionService;
import com.shangma.service.goods.GoodsService;
import com.shangma.service.goods.PriceAdjustmentService;
import com.shangma.service.goods.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional
public class ActivityPromotionServiceImpl extends BaseServiceImpl<ActivityPromotion> implements ActivityPromotionService {
    @Autowired
    private ActivityPromotionMapper mapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PriceAdjustmentService priceService;

    /**
     * 查询所有加分页
     */
    @Override
    public PageBean<ActivityPromotion> list(Integer pageSize, Integer pageNumber) {
        PageHelper.startPage(pageSize, pageNumber);
        List<ActivityPromotion> selectList = mapper.select();
        PageInfo<ActivityPromotion> pageInfo = new PageInfo<>(selectList);
        long total = pageInfo.getTotal();
        return PageBean.initData(total, selectList);
    }

    /**
     * 模糊查询加分页
     */
    @Override
    public PageBean<ActivityPromotion> search(ActivityPromotion activityPromotion, Integer pageSize, Integer pageNumber) {
        PageHelper.startPage(pageSize, pageNumber);
        List<ActivityPromotion> promotions = mapper.search();
        PageInfo<ActivityPromotion> pageInfo = new PageInfo<>(promotions);
        long total = pageInfo.getTotal();
        return PageBean.initData(total, promotions);
    }


    /**
     * 增加活动商品，取出活动商品的数量，从商品列表中减去该数量
     */
    public int increase(ActivityPromotion activityPromotion) {

        //在价格变动表中加入activityPromotion.getPriceAdjustment()   嵌套的实体类
        priceService.add(activityPromotion.getPriceAdjustment());
        //查询刚加入价格变动表的数据id
        Long lastId = priceService.lastId();

        //将id注入activityPromotion
        activityPromotion.setPId(lastId);
        System.out.println("===========================");
        System.out.println(activityPromotion);
        //在活动表中加上该条数据
        int row = mapper.insert(activityPromotion);

        return row;
    }

    /**
     * 修改未审核记录
     */
    @Override
    public int update(ActivityPromotion activityPromotion) {
        Long pId = activityPromotion.getPId();
        activityPromotion.getPriceAdjustment().setId(pId);
        
        System.out.println(activityPromotion.getPriceAdjustment());
        
        int update = priceService.update(activityPromotion.getPriceAdjustment());
        int update1 = mapper.update(activityPromotion);
        
        if (update > 0 && update1 > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 审核
     */
    public int review(ActivityPromotion activityPromotion) {
        int row = 0;
        //取出增加活动商品的数量和商品id
        Integer discountNum = activityPromotion.getPriceAdjustment().getDiscountNum();
        Long id = activityPromotion.getPriceAdjustment().getGoodsId();
        Long pId = activityPromotion.getPId();
    
        if (activityPromotion.getAuditState() == 1) {
            //当审核通过时再次去物品表去查询该物品的数量，防止数据库中商品数量为负数
            Integer GoodsNum = goodsService.getById(id).getMinStocks();

            if (GoodsNum > discountNum) {
                //当审核状态为已审核时修改当前物品的状态
                 mapper.update(activityPromotion);
                //当审核状态为审核通过减去该物品在物品表的数量，调用物品表service层的方法
               return goodsService.reduce(discountNum, id);
            }else {
                //库存小于转移量，将转移所有库存
                activityPromotion.getPriceAdjustment().setDiscountNum(GoodsNum);
                activityPromotion.getPriceAdjustment().setId(pId);
                priceService.update(activityPromotion.getPriceAdjustment());
                goodsService.reduce(GoodsNum, id);
                return mapper.update(activityPromotion);
            }
        } else if (activityPromotion.getAuditState() == 2) {
            //审核不通过修改该条数据状态值
           return mapper.update(activityPromotion);
        }
        return row;
    }
}
