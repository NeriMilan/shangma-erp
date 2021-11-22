package com.shangma.entity.sale.b2c;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("t_b2c_internet_sale_order")
public class InternetSaleOrder {
  @TableId(type = IdType.AUTO)
  private Long orderId;
  private String orderType;
  private String orderAction;
  private String businessType;
  private String payment;
  private String distributionMode;
  private String orderStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime orderDate;
  private Long customerId;
  private Character exportStatus;
  private String shopType;

  //订单商品信息
  @TableField(exist = false)
  private List<OrderGoods> goods;
  //收货人
  @TableField(exist = false)
  private Customer customer;
  //商品名称
  @TableField(exist = false)
  private List<String> goodsNames;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeFrom;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeTo;
}
