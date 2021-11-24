package com.shangma.service.afterSales;

import com.shangma.entity.afterSales.EnterMareHouse;

import java.util.List;

public interface EnterMareHouseService {
    List<EnterMareHouse> selectBySearch(EnterMareHouse enterMareHouse);
    int delByids(List<Long> ids);
    int insert(EnterMareHouse enterMareHouse);
    EnterMareHouse findById(Long id);

}
