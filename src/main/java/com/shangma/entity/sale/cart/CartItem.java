package com.shangma.entity.sale.cart;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName:CartItem
 * @Description: TODO
 * @Author:
 * @Date:2021/11/18 10:19
 * @Version: JDK1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cartitem")
public class CartItem {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long cartId;
    private Long goodId;
    private Integer goodNum;
    private Integer itemStatus;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private Goods goods;
}
