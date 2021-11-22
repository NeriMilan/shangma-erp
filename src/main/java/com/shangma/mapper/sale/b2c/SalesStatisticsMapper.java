package com.shangma.mapper.sale.b2c;

import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.SalesStatistics;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

/**
 * @CreateTime: 2021/11/19  11:12
 */
public interface SalesStatisticsMapper extends MyMapper<SalesStatistics> {

    //遍历表中数据，展示
    List<SalesStatistics> getRank();
    //根据模糊查询展示数据
    List<SalesStatistics> getRankBySearch(SalesStatistics salesStatistics);
}
