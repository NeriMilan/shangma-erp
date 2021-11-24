package com.shangma.service.afterSales;

import com.shangma.entity.afterSales.AfterSalesGoodsInformation;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.entity.goods.Goods;

import java.util.List;


public interface AfterSalesGoodsService {
    List<AfterSalesGoodsInformation> findById(Long id);
    int insert(AfterSalesGoodsInformation afterSalesGoodsInformation);
    int update(AfterSalesGoodsInformation afterSalesGoodsInformation);

    int insertAfterSales(AfterSalesInformation afterSalesInformation);
}
