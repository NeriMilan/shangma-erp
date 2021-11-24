package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class GoodsBrand {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String brandName;

    private String brandAddress;

    private String brandDescrible;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;


}