package com.shangma.service.sale.b2c.impl;

import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.mapper.sale.b2c.OnlineOrderMapper;
import com.shangma.service.sale.b2c.OnlineOrderService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2021/11/22  11:55
 */
@Service
public class OnlineOrderServiceImpl extends BaseServiceImpl<InternetSaleOrder> implements OnlineOrderService {
    @Autowired
    private OnlineOrderMapper onlineOrderMapper;
    @Override
    public List<InternetSaleOrder> search(InternetSaleOrder internetSaleOrder) {
        return onlineOrderMapper.findAll();
    }

    @Override
    public List<InternetSaleOrder> searchByStatus(InternetSaleOrder internetSaleOrder) {
        return null;
    }

    @Override
    public void exportOrder(List<Long> orderIds) {
        /**
         *  //1. 修改状态
         *     Integer updateStatus(List<Long> orderIds);
         *     //2.1. 根据传来的id获取订单集合
         *     List<InternetSaleOrder> getOrders(List<Long> orderIds);
         *     //2.2. 导出到总订单数据库
         *     Integer addOrder(List<InternetSaleOrder> list);
         */
    }
}
