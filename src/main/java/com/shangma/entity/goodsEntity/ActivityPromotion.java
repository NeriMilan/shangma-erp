package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("t_activity_promotion")
public class ActivityPromotion {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Date activityStart;

    private Date activityEnd;

    private String activityName;

    private int activityType;

    private int auditState;

    private String auditPeople;

    private String activityFile;

    private String createBy;

    private Date createTime;

    private Long pId;

    private PriceAdjustment priceAdjustment;
}