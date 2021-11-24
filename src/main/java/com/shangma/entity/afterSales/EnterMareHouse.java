package com.shangma.entity.afterSales;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnterMareHouse {
    private Long id;
    private String sign;
    private String producer;
    private LocalDateTime produceTime;
    private String approver;
    private String approverStatus;

}
