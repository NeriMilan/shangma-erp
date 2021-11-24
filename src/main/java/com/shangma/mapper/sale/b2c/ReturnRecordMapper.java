package com.shangma.mapper.sale.b2c;

import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.ReturnEntityRecord;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

/**
 * @CreateTime: 2021/11/24  16:12
 */
public interface ReturnRecordMapper extends MyMapper<ReturnEntityRecord> {
    List<ReturnEntityRecord> findAllOrder();
    List<ReturnEntityRecord> search(ReturnEntityRecord returnEntityRecord);
    ReturnEntityRecord findByOrderId(Long orderId);
    Customer findCustomer(Long id);
    List<OrderGoods> findGoods(Long orderId);
}
