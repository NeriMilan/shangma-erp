package com.shangma.service.sale.b2c;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.service.sale.b2c.base.BaseService;

import java.util.List;

/**
 * @CreateTime: 2021/11/22  11:53
 */
public interface OnlineOrderService extends BaseService<InternetSaleOrder> {
    // 初始化网店订单页面
    List<InternetSaleOrder> findAll();
    // 根据订单id查询订单下商品信息
    List<OrderGoods> findGoods(Long orderId);
    // 根据模糊查询信息 展示页面
    PageBean search(InternetSaleOrder internetSaleOrder,Integer currentPage,Integer pageSize);
    // 根据orderId 查询该订单的详细信息
    InternetSaleOrder findInfoByOrderId(Long OrderId);
    // 根据网店类型 和是否已导出状态 展示该网店类型下的订单信息
    PageBean searchByStatus(InternetSaleOrder internetSaleOrder,Integer currentPage,Integer pageSize);
    // 通过前端传来的订单id集合 修改导出状态，并将这些订单添加到总订单和出库订单数据库中
    void exportOrder(List<Long> idList);

}
