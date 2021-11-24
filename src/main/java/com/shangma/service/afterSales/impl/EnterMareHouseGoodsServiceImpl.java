package com.shangma.service.afterSales.impl;

import com.shangma.entity.afterSales.EnterMareHouseGoods;
import com.shangma.mapper.afterSales.EnterMareHouseGoodsMapper;
import com.shangma.service.afterSales.EnterMareHouseGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterMareHouseGoodsServiceImpl implements EnterMareHouseGoodsService {
    @Autowired
    private EnterMareHouseGoodsMapper enterMareHouseGoodsMapper;
    @Override
    public EnterMareHouseGoods findOrderGoods(EnterMareHouseGoods enterMareHouseGoods) {
        EnterMareHouseGoods orderGoods = enterMareHouseGoodsMapper.findOrderGoods(enterMareHouseGoods);
        System.out.println(orderGoods);
        return orderGoods;
    }

    @Override
    public int insert(EnterMareHouseGoods enterMareHouseGoods) {
        return enterMareHouseGoodsMapper.insert(enterMareHouseGoods);
    }

    @Override
    public int update(EnterMareHouseGoods enterMareHouseGoods) {
        return enterMareHouseGoodsMapper.updateByOne(enterMareHouseGoods);
    }

    @Override
    public EnterMareHouseGoods findById(Long goodId) {
        return enterMareHouseGoodsMapper.selectById(goodId);
    }


}
