package com.shangma.entity.sale.b2c;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("t_b2c_sales_return_entity_manage")
public class ReturnEntityManage {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Long orderId;
  private String orderType;
  private String orderAction;
  private String businessType;
  private String payment;
  private String distributionMode;
  private String orderStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime orderDate;
  private Long customerId;
  private String rejectionReason;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime rejectionDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime signoffTime;

  private String creator;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime creatDate;
  private String trackingNumber;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  // 下单时间
  private LocalDateTime timeFrom01;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeTo01;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  // 拒收时间
  private LocalDateTime timeFrom02;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeTo02;
  //订单商品信息
  @TableField(exist = false)
  private List<OrderGoods> goods;
  //收货人
  @TableField(exist = false)
  private Customer customer;
  //退货信息
  @TableField(exist = false)
  private RejectionInfo rejectionInfo;
  // 客户姓名，用来接收模糊查询信息
  @TableField(exist = false)
  private String customerName;
  // 客户地址，用来接收模糊查询信息
  @TableField(exist = false)
  private String customerAddress;
}
