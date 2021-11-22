package com.shangma.mapper.sale.cart;

import com.shangma.entity.sale.cart.Cart;
import com.shangma.entity.sale.cart.CartItem;
import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface CartMapper extends MyMapper<CartItem> {
    CartItem selectByGoodId(long id);
    List<CartItem> selectCart(ReceiveEntity receiveEntity);//当用户点击查看购物车时，把该用户的ID传递进来，找到该用户ID对应的购物车ID，多表关联查询，购物车项，购物车，商品
    Cart selectByUserId(long id);
}
