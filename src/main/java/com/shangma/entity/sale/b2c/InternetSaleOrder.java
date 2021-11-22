package com.shangma.entity.sale.b2c;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_b2c_internet_sale_order")
public class InternetSaleOrder {
  @TableId(type = IdType.AUTO)
  private long id;
  private long orderId;
  private String orderType;
  private String orderAction;
  private String businessType;
  private String payment;
  private String distributionMode;
  private String orderStatus;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime orderDate;
  private long customerId;
  private Character exportStatus;
  private String shopType;

}
