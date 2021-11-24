package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class FreeGoods {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String goodsName;

    private Long typeId;

    private String goodsModel;

    private Double unitPrice;

    private Double buyPrice;

    private Integer minStocks;

    private String goodsColor;

    private Long brandId;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;


}