package com.shangma.service.sale.b2c;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.StockOutManage;
import com.shangma.service.sale.b2c.base.BaseService;

import java.util.List;

/**
 * @CreateTime: 2021/11/23  11:37
 */
public interface StockOutService extends BaseService<StockOutManage> {
    PageBean findAllStockOut(Integer currentPage,Integer pageSize);
    PageBean search(StockOutManage stockOutManage,Integer currentPage,Integer pageSize);
    StockOutManage getOrderInfo(Long orderId);
    void generateRecord(List<Long> orderIds,String userName);
}
