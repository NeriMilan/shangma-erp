package com.shangma.service.afterSales.impl;

import com.shangma.entity.afterSales.EnterMareHouse;
import com.shangma.mapper.afterSales.EnterMareHouseMapper;
import com.shangma.service.afterSales.EnterMareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnterMareHouseServiceImpl implements EnterMareHouseService {
@Autowired
private EnterMareHouseMapper enterMareHouseMapper;

    @Override
    public List<EnterMareHouse> selectBySearch(EnterMareHouse enterMareHouse) {
        return enterMareHouseMapper.selectBySearch(enterMareHouse);
    }
}
