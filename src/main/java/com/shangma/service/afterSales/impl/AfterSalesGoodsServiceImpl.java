package com.shangma.service.afterSales.impl;

import com.shangma.entity.afterSales.AfterSalesGoodsInformation;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.entity.goods.Goods;
import com.shangma.mapper.afterSales.AfterSalesGoodsMapper;
import com.shangma.mapper.afterSales.AfterSalesMapper;
import com.shangma.service.afterSales.AfterSalesGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfterSalesGoodsServiceImpl implements AfterSalesGoodsService {
    @Autowired
    private AfterSalesGoodsMapper afterSalesGoodsMapper;
    @Autowired
    private AfterSalesMapper afterSalesMapper;

    @Override
    public List<AfterSalesGoodsInformation> findById(Long id) {
        return afterSalesGoodsMapper.findByOrderId(id);
    }

    @Override
    public int insert(AfterSalesGoodsInformation afterSalesGoodsInformation) {
        return afterSalesGoodsMapper.insert(afterSalesGoodsInformation);
    }

    @Override
    public int update(AfterSalesGoodsInformation afterSalesGoodsInformation) {
        return afterSalesGoodsMapper.updateById(afterSalesGoodsInformation);
    }

    @Override
    public int insertAfterSales(AfterSalesInformation afterSalesInformation) {
        return afterSalesMapper.insert(afterSalesInformation);
    }


}
