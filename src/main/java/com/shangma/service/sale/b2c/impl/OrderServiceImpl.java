package com.shangma.service.sale.b2c.impl;

import com.shangma.entity.sale.b2c.SalesOrder;
import com.shangma.mapper.sale.b2c.OrderMapper;
import com.shangma.service.sale.b2c.OrderService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @CreateTime=2021/11/17
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<SalesOrder> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
}
