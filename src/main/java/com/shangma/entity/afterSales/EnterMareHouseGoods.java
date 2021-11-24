package com.shangma.entity.afterSales;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("enter_marehouse_goods")
public class EnterMareHouseGoods {
    private Long goodId;
    private String brandName;
    private String goodsName;
    private String goodsModel;
    private String goodsColor;
    private String goodsTrace;
    private String returnType;
    private Long secondId;
    private String factoryAddress;
    private String remark;
    private Long orderId;

}
