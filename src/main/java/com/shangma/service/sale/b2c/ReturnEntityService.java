package com.shangma.service.sale.b2c;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.ReturnEntityManage;
import com.shangma.service.sale.b2c.base.BaseService;

import java.util.List;

/**
 * @CreateTime: 2021/11/24  11:30
 */
public interface ReturnEntityService extends BaseService<ReturnEntityManage> {
    PageBean findAllOrder(Integer currentPage, Integer pageSize);
    PageBean Search(Integer currentPage, Integer pageSize,ReturnEntityManage returnEntityManage);
    ReturnEntityManage getOrderInfo(Long orderId);
    void generateRecord(ReturnEntityManage returnEntityManage);
}
