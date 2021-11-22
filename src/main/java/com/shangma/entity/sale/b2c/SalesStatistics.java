package com.shangma.entity.sale.b2c;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName("t_b2c_sales_statistics")
public class SalesStatistics {
  @TableId(type = IdType.AUTO)
  private Long id;
  @TableField("goods_name")
  private String goodName;
  @TableField("goods_type")
  private String typeName;
  @TableField("goods_brand")
  private String brandName;
  @TableField("goods_version")
  private String goodsModel;
  @TableField("goods_color")
  private String goodsColor;
  @TableField("goods_sales")
  private Long totalCount;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField("goods_saletime")
  private LocalDateTime goodsSaletime;
  @TableField(exist = false)
  private OrderGoods orderGoods;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeFrom;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableField(exist = false)
  private LocalDateTime timeTo;
}
