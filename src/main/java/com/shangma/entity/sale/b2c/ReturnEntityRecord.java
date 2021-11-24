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
@TableName("t_b2c_sales_return_entity_record")
public class ReturnEntityRecord {
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

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeFrom;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeTo;

  @TableField(exist = false)
  private List<OrderGoods> orderGoods;
  @TableField(exist = false)
  private Customer customer;

  //拒收原因
  @TableField(exist = false)
  private RejectionInfo rejectionInfo;
}
