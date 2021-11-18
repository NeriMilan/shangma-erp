package com.shangma.entity.sale.b2c;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_b2c_order_goods")
public class OrderGoods {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Long orderId;
  private Long goodId;
  private String goodName;
  private Double goodPrice;
  private Long goodCount;
  private Double totalAmount;


}
