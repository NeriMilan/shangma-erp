package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.entity.base.BaseEntity;
import lombok.Data;

@Data
@TableName("t_free_goods")
public class FreeGoods extends BaseEntity {

    private String goodsName;

    private Long typeId;

    private String goodsModel;

    private Double unitPrice;

    private Double buyPrice;

    private Integer minStocks;

    private String goodsColor;

    private Long brandId;

    @TableField(exist = false)
    private String brandName;

    @TableField(exist = false)
    private String typeName;

}