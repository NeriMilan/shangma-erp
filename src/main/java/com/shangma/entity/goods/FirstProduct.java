package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_first_product")
public class FirstProduct {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long typeId;

    private Long brandId;

    private String goodsModel;

    private String goodsColor;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String brandName;

//    private GoodsBrand goodsBrand;

}