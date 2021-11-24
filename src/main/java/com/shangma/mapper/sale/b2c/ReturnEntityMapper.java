package com.shangma.mapper.sale.b2c;

import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.ReturnEntityManage;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

/**
 * @CreateTime: 2021/11/24  9:53
 */
public interface ReturnEntityMapper extends MyMapper<ReturnEntityManage> {
    /**
     * 1. 初始化界面信息
     * 2. 根据模糊查询信息返回
     * 3. 根据orderId查询该订单详细信息
     * 4. 生成退货单：增加物流单号，退货时间和原因，退货单的创建人和创建时间
     */
    List<ReturnEntityManage> findAllOrder();
    List<ReturnEntityManage> findBySearch(ReturnEntityManage returnEntityManage);
    ReturnEntityManage findById(Long orderId);
    Customer findCustomer(Long id);
    List<OrderGoods> findGoodsById(Long orderId);
    Integer deleteByOrderId(Long orderId);
    void addReturnRecord(ReturnEntityManage returnEntityManage);
}
