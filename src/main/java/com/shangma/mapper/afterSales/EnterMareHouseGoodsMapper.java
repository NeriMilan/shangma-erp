package com.shangma.mapper.afterSales;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.afterSales.EnterMareHouseGoods;

public interface EnterMareHouseGoodsMapper extends BaseMapper<EnterMareHouseGoods> {

    EnterMareHouseGoods findOrderGoods(EnterMareHouseGoods enterMareHouseGoods);
    EnterMareHouseGoods selectById(Long goodId);
    int updateByOne(EnterMareHouseGoods enterMareHouseGoods);
}
