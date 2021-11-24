package com.shangma.entity.afterSales;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("after_sales_information")
public class AfterSalesInformation implements Serializable {
    private Long id;

    private String producer;

    private LocalDateTime produceTime;

    private String approver;

    private LocalDateTime approverTime;

    private String approveStatus;

}