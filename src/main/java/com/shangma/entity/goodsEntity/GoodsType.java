package com.shangma.entity.goodsEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class GoodsType {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String typeName;

    private String typeDescrible;

    private Long brandId;

    private Integer typePid;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;


}