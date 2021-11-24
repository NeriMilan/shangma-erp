package com.shangma.mapper.afterSales;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.afterSales.AfterSalesGoodsInformation;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.entity.goods.Goods;

import java.util.List;


public interface AfterSalesGoodsMapper extends BaseMapper<AfterSalesGoodsInformation> {
    List<AfterSalesGoodsInformation> findByOrderId(Long id);



}
