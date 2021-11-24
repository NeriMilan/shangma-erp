package com.shangma.mapper.sale.b2c;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.StockOutManage;
import com.shangma.mapper.base.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @CreateTime: 2021/11/23  11:01
 */
public interface StockOutMapper extends MyMapper<StockOutManage> {
    /**
     * 1.初始化页面
     * 2.根据模糊搜索信息展示界面
     * 3.根据OrderId展示订单详细信息
     * todo :4.根据orderId生成出库记录单，修改状态为已出库，增加创建人和创建时间两个字段信息
     */
    List<StockOutManage> findAll();
    List<StockOutManage> search(StockOutManage stockOutManage);
    //1.根据orderId获取到订单信息
    StockOutManage getOrderInfo(Long orderId);
    //2.根据customerId获取到客户信息
    Customer findCustomer(@Param("id") Long customerId);
    //3.根据orderId获取到商品清单信息
    List<OrderGoods> findGoods(Long orderId);
    /**
     * 修改订单状态为已出库
     * 根据orderId将获取到的订单信息添加到出库记录中
     */
    Integer updateOrderStatus(Long orderId);
    Integer updateOnlineOrderStatus(Long orderId);
    Integer updateStockOrderStatus(Long orderId);
    Integer addStockOutRecord(StockOutManage stockOutManage);
    List<StockOutManage> findByIds(@Param("orderIds") List<Long> orderIds);
}
