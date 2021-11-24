package com.shangma.mapper.sale.b2c;

import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.mapper.base.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @CreateTime: 2021/11/22  11:09
 */
public interface OnlineOrderMapper extends MyMapper<InternetSaleOrder> {
    // 初始化网店订单页面
    List<InternetSaleOrder> findAll();
    // 根据订单id去查询当前订单下的商品清单
    List<OrderGoods> findGoods(Long orderId);
    // 根据模糊查询信息 展示页面
    List<InternetSaleOrder> search(InternetSaleOrder internetSaleOrder);
    // 详细查询，根据订单id查询 订单详情，购买人信息，购买商品列表
    Customer findCustomerById(Long customerId);
    List<OrderGoods> findGoodsByOrderId(Long orderId);
    InternetSaleOrder findOrderById(Long orderId);
    // 根据网店类型 和是否已导出状态 展示该网店类型下的订单信息
    List<InternetSaleOrder> searchByStatus(InternetSaleOrder internetSaleOrder);
    // 通过前端传来的订单id集合 修改导出状态，并将这些订单添加到总订单数据库中
    //1. 修改状态
    Integer updateStatus(Long orderId);
    //2.1. 根据传来的id获取订单集合
    List<InternetSaleOrder> getOrders(@Param("idList") List<Long> idList);
    //2.2. 导出到总订单数据库
    Integer addOrder( InternetSaleOrder internetSaleOrder);
    //2.3. 导出到出库管理中
    Integer addToStockOrder(InternetSaleOrder internetSaleOrder);
}
