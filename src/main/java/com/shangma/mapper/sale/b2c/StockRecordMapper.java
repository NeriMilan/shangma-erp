package com.shangma.mapper.sale.b2c;

import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.StockOutRecord;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

/**
 * @CreateTime: 2021/11/23  16:49
 */
public interface StockRecordMapper extends MyMapper<StockOutRecord> {
    List<StockOutRecord> findAllRecord();
    List<StockOutRecord> searchByTerm(StockOutRecord stockOutRecord);
    StockOutRecord findRecordByOrderId(Long orderId);
    List<OrderGoods> findGoods(Long orderId);
    Customer findCustomer(Long customerId);
}
