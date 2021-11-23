package com.shangma.mapper.afterSales;

import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.mapper.base.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AfterSalesMapper extends MyMapper<AfterSalesInformation> {
    /**
     * 根据制表时间查询
     * @param startTime
     * @param endTime
     * @return
     */
    List<AfterSalesInformation> findByProduceTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);



}
