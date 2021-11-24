package com.shangma.service.afterSales;

import com.shangma.entity.afterSales.EnterMareHouseGoods;

import java.util.List;

public interface EnterMareHouseGoodsService {
    EnterMareHouseGoods findOrderGoods(EnterMareHouseGoods enterMareHouseGoods);
    int insert(EnterMareHouseGoods enterMareHouseGoods);
    int update(EnterMareHouseGoods enterMareHouseGoods);
    EnterMareHouseGoods findById(Long goodId);


}
