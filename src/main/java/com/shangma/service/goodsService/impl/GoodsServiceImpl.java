package com.shangma.service.goodsService.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goodsEntity.Goods;
import com.shangma.mapper.goodsMapper.GoodsMapper;
import com.shangma.service.goodsService.GoodsService;
import com.shangma.service.goods.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模糊查询加分页
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageBean<Goods> list(Integer pageSize, Integer pageNumber) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> goods = goodsMapper.selectList(null);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);
        long total = goodsPageInfo.getTotal();
        return PageBean.initData(total, goods);

    }

    public PageBean<Goods> search(Goods goods, Integer pageSize, Integer pageNumber) {

        PageHelper.startPage(pageNumber, pageSize);

        List<Goods> goodsList = goodsMapper.search(goods);
        //获取所有的页面信息
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        return PageBean.initData(total, goodsList);
    }



    public Integer reduce(Integer num, Long id) {
        return goodsMapper.reduce(num, id);
    }


    //加上商品的数量
    public Integer plus(Integer num,Long id){
        return  goodsMapper.plus(num, id);

    };
}
