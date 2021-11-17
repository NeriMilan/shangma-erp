package com.shangma.entity.sale.b2c;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_b2c_customer")
public class Customer {

  @TableId(type = IdType.AUTO)
  private long id;
  private String customerName;
  private String customerAccount;
  private String customerPhone;
  private String customerPostcode;
  private String customerAddress;
  private String customerArea;
  private String distributionMode;
  private double distributionCost;
  private String customerPayway;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime takeTheirTime;
  private String customerEmail;
  private String remitter;
  private String notes;

}
