package com.shangma.entity.sale.b2c;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_b2c_sales_statistics")
public class SalesStatistics {
  @TableId(type = IdType.AUTO)
  private long id;
  private String goodsName;
  private String goodsType;
  private String goodsBrand;
  private String goodsVersion;
  private String goodsColor;
  private long goodsSales;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime goodsSaleiime;

}
