package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.entity.base.BaseEntity;
import lombok.Data;

@Data
@TableName("t_goods_type")
public class GoodsType extends BaseEntity {

    private String typeName;

    private String typeDescrible;

//    private Long brandId;

    private Integer typePid;

//    private String brandName;


}