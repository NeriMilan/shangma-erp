package com.shangma.service.sale.b2c;

import com.shangma.entity.sale.b2c.SalesStatistics;
import com.shangma.service.sale.b2c.base.BaseService;

import java.util.List;

/**
 * @CreateTime: 2021/11/19  11:10
 */
public interface SalesStatisticsService extends BaseService<SalesStatistics> {
    //遍历表中数据，展示
    List<SalesStatistics> getRank();
    //根据模糊查询展示数据
    List<SalesStatistics> getRankBySearch(SalesStatistics salesStatistics);
}
