package com.shangma.service.afterSales;

import com.shangma.entity.afterSales.AfterSalesInformation;

import java.util.List;

public interface AfterSalesService {
    /**
     * 查询全部
     */
    List<AfterSalesInformation> list();
    /**
     * 分页
     */
    List<AfterSalesInformation> findAllByPage(Integer pageIndex, Integer pageSize);
    /**
     * 根据id进行查询
     */
    AfterSalesInformation findById(Long id);
    /**
     * 根据制单人进行查询
     */
    List<AfterSalesInformation> findByProducer(String producer);
    /**
     * 根据时间段进行查询
     * @param startTime
     * @param endTime
     */
    List<AfterSalesInformation> findByProduceTime(long startTime, long endTime);
    /**
     * 删除一个或多个
     * @param ids 根据id删
     */
   int delByIds(List<Long> ids);
   int delById(Long id);

    /**
     * 修改
     *
     * @param afterSalesInformation
     */
    int update(AfterSalesInformation afterSalesInformation);


}
