package com.shangma.service.sale.b2c.impl;

import com.shangma.entity.sale.b2c.SalesStatistics;
import com.shangma.mapper.sale.b2c.SalesStatisticsMapper;
import com.shangma.service.sale.b2c.SalesStatisticsService;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2021/11/19  11:11
 */

@Service
public class SalesStatisticsServiceImpl extends BaseServiceImpl<SalesStatistics> implements SalesStatisticsService {
    @Autowired
    private SalesStatisticsMapper statisticsMapper;

    @Override
    public List<SalesStatistics> getRank() {
        return statisticsMapper.getRank();
    }

    @Override
    public List<SalesStatistics> getRankBySearch(SalesStatistics salesStatistics) {
        return statisticsMapper.getRankBySearch(salesStatistics);
    }
}
