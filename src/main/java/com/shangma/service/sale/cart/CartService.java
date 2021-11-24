package com.shangma.service.sale.cart;


import com.github.pagehelper.PageInfo;
import com.shangma.entity.sale.cart.Cart;
import com.shangma.entity.sale.cart.CartItem;
import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.service.sale.b2c.base.BaseService;

import java.util.List;

public interface CartService extends BaseService<CartItem> {
    CartItem queryByGoodId(long id);
    PageInfo<CartItem> queryCart(ReceiveEntity receiveEntity);
    Cart queryByUserId(long id);

    Integer pay(List<Long> cartItemIds,long userId);
}
