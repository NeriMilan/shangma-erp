package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.entity.goods.ActivityGoods;
import lombok.Data;

import java.util.Date;

/**
 * 市场活动中有活动赠品和中奖人信息
 */
@Data
@TableName("t_activity_market")
public class ActivityMarket {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String activityName;

    private Integer rewardWay;

    private String applicant;

    private Date activityStart;

    private Date activityEnd;

    private Long freebiesId;

    private Integer freebiesNum;

    private String applicationMatters;

    private String approvalPeople;

    private Date approvalTime;

    private Integer approvalState;

    private String approvalOpinion;

    private Long gId;


    //活动赠品
    private ActivityGoods activityGoods;


}
