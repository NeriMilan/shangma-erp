package com.shangma.service.sale.b2c.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.StockOutRecord;
import com.shangma.mapper.sale.b2c.StockRecordMapper;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2021/11/23  17:24
 */
@Service
public class StockRecordService extends BaseServiceImpl<StockOutRecord> implements com.shangma.service.sale.b2c.StockRecordService {
    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Override
    public PageBean findAllRecord(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<StockOutRecord> list = stockRecordMapper.findAllRecord();
        PageInfo<StockOutRecord> pageInfo = PageInfo.of(list);
        List<StockOutRecord> list1 = pageInfo.getList();
        long total = pageInfo.getTotal();
        return PageBean.initData(total,list1);
    }

    @Override
    public PageBean findBySearch(StockOutRecord stockOutRecord,Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<StockOutRecord> search = stockRecordMapper.searchByTerm(stockOutRecord);
        PageInfo<StockOutRecord> pageInfo = PageInfo.of(search);
        List<StockOutRecord> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        return PageBean.initData(total,list);
    }

    @Override
    public StockOutRecord getOrderInfo(Long orderId) {
        StockOutRecord stockOutRecord = stockRecordMapper.findRecordByOrderId(orderId);
        Long customerId = stockOutRecord.getCustomerId();
        Customer customer = stockRecordMapper.findCustomer(customerId);
        stockOutRecord.setCustomer(customer);
        List<OrderGoods> goods = stockRecordMapper.findGoods(orderId);
        stockOutRecord.setOrderGoods(goods);
        return stockOutRecord;
    }
}
