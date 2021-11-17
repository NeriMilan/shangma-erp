package com.shangma.entity.sale.b2c;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_b2c_order_goods")
public class OrderGoods {
  @TableId(type = IdType.AUTO)
  private long id;
  private long orderId;
  private long goodId;
  private String goodName;
  private double goodPrice;
  private long goodCount;
  private double totalAmount;


}
