package com.shangma.service.sale.b2c;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.ReturnEntityRecord;
import com.shangma.service.sale.b2c.base.BaseService;

/**
 * @CreateTime: 2021/11/24  16:44
 */
public interface ReturnRecordService extends BaseService<ReturnEntityRecord> {
    PageBean findAllOrder(Integer currentPage,Integer pageSize);
    PageBean findBySearch(Integer currentPage,Integer pageSize,ReturnEntityRecord returnEntityRecord);
    ReturnEntityRecord getOrderInfo(Long orderId);
}
