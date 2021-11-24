package com.shangma.service.sale.b2c.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.ReturnEntityManage;
import com.shangma.mapper.sale.b2c.ReturnEntityMapper;
import com.shangma.service.sale.b2c.ReturnEntityService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateTime: 2021/11/24  11:37
 */
@Service
public class ReturnEntityServiceImpl extends BaseServiceImpl<ReturnEntityManage> implements ReturnEntityService {
    @Autowired
    private ReturnEntityMapper returnEntityMapper;
    @Override
    public PageBean findAllOrder(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<ReturnEntityManage> list = returnEntityMapper.findAllOrder();
        PageInfo<ReturnEntityManage> pageInfo = PageInfo.of(list);
        return PageBean.initData(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public PageBean Search(Integer currentPage, Integer pageSize, ReturnEntityManage returnEntityManage) {
        PageHelper.startPage(currentPage,pageSize);
        List<ReturnEntityManage> list = returnEntityMapper.findBySearch(returnEntityManage);
        PageInfo<ReturnEntityManage> pageInfo = PageInfo.of(list);
        return PageBean.initData(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public ReturnEntityManage getOrderInfo(Long orderId) {
        ReturnEntityManage returnEntityManage = returnEntityMapper.findById(orderId);
        Long customerId = returnEntityManage.getCustomerId();
        Customer customer = returnEntityMapper.findCustomer(customerId);
        returnEntityManage.setCustomer(customer);
        List<OrderGoods> goods = returnEntityMapper.findGoodsById(orderId);
        returnEntityManage.setGoods(goods);
        return returnEntityManage;
    }


    @Override
    @Transactional
    public void generateRecord(ReturnEntityManage returnEntityManage) {
        Long orderId = returnEntityManage.getOrderId();
        ReturnEntityManage returnEntityManage01 = returnEntityMapper.findById(orderId);
        if (returnEntityManage.getRejectionDate() != null){
            returnEntityManage01.setRejectionDate(returnEntityManage.getRejectionDate());
        }
        if (returnEntityManage.getRejectionReason() != null && !"".equals(returnEntityManage.getRejectionReason())){
            returnEntityManage01.setRejectionReason(returnEntityManage.getRejectionReason());
        }
        returnEntityManage01.setCreator(returnEntityManage.getCreator());
        returnEntityMapper.addReturnRecord(returnEntityManage01);
        returnEntityMapper.deleteByOrderId(orderId);
    }
}
