package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.entity.base.BaseEntity;
import lombok.Data;

@Data
@TableName("t_goods")
public class Goods extends BaseEntity {

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
    @TableField(exist = false)
    private String brandName;

    //    private FirstProduct firstProduct;
    @TableField(exist = false)
    private String typeName;


//    private FirstGoods firstGoods;
//
//    private GoodsType goodsType;


}