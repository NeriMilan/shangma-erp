package com.shangma.mapper.afterSales;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.afterSales.EnterMareHouse;

import java.util.List;

public interface EnterMareHouseMapper extends BaseMapper<EnterMareHouse> {
    List<EnterMareHouse> selectBySearch(EnterMareHouse enterMareHouse);


}
