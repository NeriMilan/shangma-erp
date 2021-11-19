package com.shangma.entity.afterSales;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("after_sales_goods_information")
public class AfterSalesGoodsInformation {
    private Long id;

    private String goodsName;

    private String goodsBrand;

    private String goodsModel;

    private String goodsColor;

    private String returnType;

    private String exchangeName;

    private String returnCause;

    private String remark;

    private String orderId;


}