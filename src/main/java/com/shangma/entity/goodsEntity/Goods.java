package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("t_goods")
public class Goods {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Integer goodsKind;

    private Long typePid;

    private Long brandId;

    private String goodsModel;

    private String goodsColor;

    private String goodsName;

    private Long typeId;

    private Double buyPrice;

    private Double marketPrice;

    private Double shopPrice;

    private Integer minStocks;

    private Integer isBuy;

    private Long productId;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;


}