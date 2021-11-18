package com.shangma.service.sale.b2c.impl;

import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.SalesOrder;
import com.shangma.mapper.sale.b2c.OrderMapper;
import com.shangma.service.sale.b2c.OrderService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime=2021/11/17
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<SalesOrder> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<SalesOrder> findBySearch(SalesOrder salesOrder) {
        return orderMapper.findBySearch(salesOrder);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return orderMapper.findCustomerById(id);
    }

    @Override
    public List<OrderGoods> findOrderGoods(Long orderId) {
        return orderMapper.findOrderGoods(orderId);
    }
}
