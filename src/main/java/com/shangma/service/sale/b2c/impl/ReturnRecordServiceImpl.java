package com.shangma.service.sale.b2c.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.ReturnEntityRecord;
import com.shangma.mapper.sale.b2c.ReturnRecordMapper;
import com.shangma.service.sale.b2c.ReturnRecordService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2021/11/24  16:49
 */
@Service
public class ReturnRecordServiceImpl extends BaseServiceImpl<ReturnEntityRecord> implements ReturnRecordService {
    @Autowired
    private ReturnRecordMapper returnRecordMapper;

    @Override
    public PageBean findAllOrder(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<ReturnEntityRecord> list = returnRecordMapper.findAllOrder();
        PageInfo<ReturnEntityRecord> pageInfo = PageInfo.of(list);
        return PageBean.initData(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public PageBean findBySearch(Integer currentPage, Integer pageSize, ReturnEntityRecord returnEntityRecord) {
        PageHelper.startPage(currentPage,pageSize);
        List<ReturnEntityRecord> list = returnRecordMapper.search(returnEntityRecord);
        PageInfo<ReturnEntityRecord> pageInfo = PageInfo.of(list);
        return PageBean.initData(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public ReturnEntityRecord getOrderInfo(Long orderId) {
        ReturnEntityRecord returnEntityRecord = returnRecordMapper.findByOrderId(orderId);
        Long customerId = returnEntityRecord.getCustomerId();
        Customer customer = returnRecordMapper.findCustomer(customerId);
        returnEntityRecord.setCustomer(customer);
        List<OrderGoods> goods = returnRecordMapper.findGoods(orderId);
        returnEntityRecord.setOrderGoods(goods);
        return returnEntityRecord;
    }
}
