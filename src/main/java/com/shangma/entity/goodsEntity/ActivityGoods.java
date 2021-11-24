package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_activity_goods")
public class ActivityGoods {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Integer freeGoodsId;

    private String freeGoodsName;

    private String freeGoodModel;

    private String freeGoodsColor;

    private Integer freeGoodsNum;

    private Integer freeGoodsResiduals;

    private Double freeGoodsMarketPrice;

    private Double freeGoodsShopPrice;

    private int rId;
    //中奖人信息
    private WinningRecord winningRecord;
}
