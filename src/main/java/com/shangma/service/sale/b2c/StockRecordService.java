package com.shangma.service.sale.b2c;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.StockOutRecord;
import com.shangma.service.sale.b2c.base.BaseService;

import java.util.List;

/**
 * @CreateTime: 2021/11/23  17:19
 */
public interface StockRecordService extends BaseService<StockOutRecord> {
    /**
     * 1.初始化界面
     * 2.根据模糊搜素信息返回界面
     * 3.根据orderId返回该订单详细信息
     */

    PageBean findAllRecord(Integer currentPage, Integer pageSize);
    PageBean findBySearch(StockOutRecord stockOutRecord,Integer currentPage,Integer pageSize);
    StockOutRecord getOrderInfo(Long orderId);
}
