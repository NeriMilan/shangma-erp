package com.shangma.entity.sale.cart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName:Cart
 * @Description: TODO
 * @Author:
 * @Date:2021/11/18 10:19
 * @Version: JDK1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cart")
public class Cart {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private List<CartItem> cartItemList;
}
