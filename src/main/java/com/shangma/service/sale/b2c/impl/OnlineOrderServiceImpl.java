package com.shangma.service.sale.b2c.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.mapper.sale.b2c.OnlineOrderMapper;
import com.shangma.service.sale.b2c.OnlineOrderService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateTime: 2021/11/22  11:55
 */
@Service
public class OnlineOrderServiceImpl extends BaseServiceImpl<InternetSaleOrder> implements OnlineOrderService {
    @Autowired
    private OnlineOrderMapper onlineOrderMapper;
    private static final Logger logger = LoggerFactory.getLogger(OnlineOrderServiceImpl.class);

    public List<InternetSaleOrder> findAll(){
        return onlineOrderMapper.findAll();
    }

    @Override
    public List<OrderGoods> findGoods(Long orderId) {
        return onlineOrderMapper.findGoods(orderId);
    }

    @Override
    public PageBean search(InternetSaleOrder internetSaleOrder,Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<InternetSaleOrder> orderList = onlineOrderMapper.search(internetSaleOrder);
        PageInfo<InternetSaleOrder> pageInfo = PageInfo.of(orderList);
        long total = pageInfo.getTotal();
        List<InternetSaleOrder> list = pageInfo.getList();
        for (int i = 0; i < list.size(); i++) {
            List<OrderGoods> goods = onlineOrderMapper.findGoods(list.get(i).getOrderId());
            String s = "";
            for (int j = 0; j < goods.size(); j++) {
                s += goods.get(j).getGoodName()+";";
            }
            logger.debug(s);
            list.get(i).setGoodsNames(s);
        }

        PageBean<InternetSaleOrder> pageBean = PageBean.initData(total, list);
        return pageBean;
    }

    @Override
    public InternetSaleOrder findInfoByOrderId(Long OrderId) {
        InternetSaleOrder saleOrder = onlineOrderMapper.findOrderById(OrderId);
        Long customerId = saleOrder.getCustomerId();
        Customer customer = onlineOrderMapper.findCustomerById(customerId);
        saleOrder.setCustomer(customer);
        List<OrderGoods> orderGoods = onlineOrderMapper.findGoodsByOrderId(OrderId);
        saleOrder.setGoods(orderGoods);
        return saleOrder;
    }

    @Override
    public PageBean searchByStatus(InternetSaleOrder internetSaleOrder,Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<InternetSaleOrder> orderList = onlineOrderMapper.searchByStatus(internetSaleOrder);
        PageInfo<InternetSaleOrder> pageInfo = PageInfo.of(orderList);
        List<InternetSaleOrder> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        PageBean<InternetSaleOrder> pageBean = PageBean.initData(total, list);
        return pageBean;
    }

    @Override
    @Transactional
    public void exportOrder(List<Long> idList) {

        List<InternetSaleOrder> internetSaleOrders = onlineOrderMapper.getOrders(idList);

        for (int i = 0; i < internetSaleOrders.size(); i++) {
            if (internetSaleOrders.get(i).getExportStatus().equals('0')){
                onlineOrderMapper.addOrder(internetSaleOrders.get(i));
                onlineOrderMapper.addToStockOrder(internetSaleOrders.get(i));
            }
        }
        for (int i = 0; i < idList.size(); i++) {
            onlineOrderMapper.updateStatus(idList.get(i));
        }
    }
}
