package com.shangma.entity.sale.b2c;


import com.baomidou.mybatisplus.annotation.IdType;
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
  private String goodsName;
  private String goodsType;
  private String goodsBrand;
  private String goodsVersion;
  private String goodsColor;
  private Long goodsSales;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime goodsSaleiime;

}
