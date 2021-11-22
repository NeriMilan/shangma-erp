package com.shangma.mapper.sale.b2c;

import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.SalesOrder;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

/**
 * @CreateTime=17:12
 */
public interface OrderMapper extends MyMapper<SalesOrder> {
    List<SalesOrder> findBySearch(SalesOrder salesOrder);
    Customer findCustomerById(Long id);
    List<OrderGoods> findOrderGoods(Long orderId);
}
