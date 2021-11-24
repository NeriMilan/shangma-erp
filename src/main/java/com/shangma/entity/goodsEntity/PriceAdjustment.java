package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("t_price_adjustment")
public class PriceAdjustment implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long goodsId;

    private String goodsName;

    private Integer goodsNum;

    private Integer marketBeforePrice;

    private Integer shopBeforePrice;

    private Integer marketNowPrice;

    private Integer shopNowPrice;

    private Integer activityPrice;

    private Integer discountNum;

    private Date priceStart;

    private Date priceEnd;

    private String activityReason;

    private Integer approvalState;
}
