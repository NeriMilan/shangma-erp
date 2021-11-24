package com.shangma.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_goods_brand")
public class GoodsBrand extends BaseEntity{
    private String brandName;

    private String brandAddress;

    private String brandDescrible;

}