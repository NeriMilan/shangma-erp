package com.shangma.mapper.sale.b2c;

import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

/**
 * @CreateTime: 2021/11/22  11:09
 */
public interface OnlineOrderMapper extends MyMapper<InternetSaleOrder> {
    // 初始化网店订单页面
    List<InternetSaleOrder> findAll();
    // 根据模糊查询信息 展示页面
    List<InternetSaleOrder> search(InternetSaleOrder internetSaleOrder);
    // 根据网店类型 和是否已导出状态 展示该网店类型下的订单信息
    List<InternetSaleOrder> searchByStatus(InternetSaleOrder internetSaleOrder);
    // 通过前端传来的订单id集合 修改导出状态，并将这些订单添加到总订单数据库中
    //1. 修改状态
    Integer updateStatus(List<Long> orderIds);
    //2.1. 根据传来的id获取订单集合
    List<InternetSaleOrder> getOrders(List<Long> orderIds);
    //2.2. 导出到总订单数据库
    Integer addOrder(List<InternetSaleOrder> list);
}
