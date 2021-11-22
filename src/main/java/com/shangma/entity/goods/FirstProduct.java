package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_first_product")
public class FirstProduct {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long typeId;

    private Long brandId;

    private String goodsModel;

    private String goodsColor;

    private String typeName;

    private String brandName;

//    private GoodsBrand goodsBrand;

}