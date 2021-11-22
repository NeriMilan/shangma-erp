package com.shangma.service.sale.b2c;

import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.SalesOrder;
import com.shangma.service.sale.b2c.base.BaseService;

import java.util.List;

/**
 * @CreateTime=17:13
 */
public interface OrderService extends BaseService<SalesOrder> {

    List<SalesOrder> findBySearch(SalesOrder salesOrder);
    Customer findCustomerById(Long id);
    List<OrderGoods> findOrderGoods(Long orderId);
}
