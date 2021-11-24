package com.shangma.service.sale.b2c.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.StockOutManage;
import com.shangma.mapper.sale.b2c.StockOutMapper;
import com.shangma.service.sale.b2c.StockOutService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateTime: 2021/11/23  11:38
 */
@Service
public class StockOutServiceImpl extends BaseServiceImpl<StockOutManage> implements StockOutService {
    @Autowired
    private StockOutMapper stockOutMapper;
    @Override
    public PageBean findAllStockOut(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<StockOutManage> manageList = stockOutMapper.findAll();
        PageInfo<StockOutManage> pageInfo = PageInfo.of(manageList);
        long total = pageInfo.getTotal();
        List<StockOutManage> outManages = pageInfo.getList();
        return PageBean.initData(total,outManages);
    }

    @Override
    public PageBean search(StockOutManage stockOutManage,Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<StockOutManage> manageList = stockOutMapper.search(stockOutManage);
        PageInfo<StockOutManage> pageInfo = PageInfo.of(manageList);
        long total = pageInfo.getTotal();
        List<StockOutManage> outManages = pageInfo.getList();
        return PageBean.initData(total,outManages);
    }
    @Override
    public StockOutManage getOrderInfo(Long orderId) {
        StockOutManage orderInfo = stockOutMapper.getOrderInfo(orderId);
        Long customerId = orderInfo.getCustomerId();
        Customer customer = stockOutMapper.findCustomer(customerId);
        orderInfo.setCustomer(customer);
        List<OrderGoods> goods = stockOutMapper.findGoods(orderId);
        orderInfo.setGoods(goods);
        return orderInfo;
    }

    @Override
    @Transactional
    public void generateRecord(List<Long> orderIds,String userName) {
        for (int i = 0; i < orderIds.size(); i++) {
            stockOutMapper.updateOrderStatus(orderIds.get(i));
            stockOutMapper.updateOnlineOrderStatus(orderIds.get(i));
            stockOutMapper.updateStockOrderStatus(orderIds.get(i));
        }
        List<StockOutManage> list = stockOutMapper.findByIds(orderIds);
        for (int i = 0; i < list.size(); i++) {
            StockOutManage stockOutManage = list.get(i);
            stockOutManage.setUserName(userName);
            stockOutMapper.addStockOutRecord(stockOutManage);
        }
    }
}
