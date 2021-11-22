package com.shangma.controller.sale.cart;

import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.entity.sale.cart.Cart;
import com.shangma.entity.sale.cart.CartItem;
import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.service.sale.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:CartController
 * @Description: TODO
 * @Author:
 * @Date:2021/11/18 10:54
 * @Version: JDK1.8
 */
@RestController
@RequestMapping("/sale/CartController/")
public class CartController {

    @Autowired
    private CartService cartService;
    private Integer row;
    @GetMapping("query")
    public AxiosResult<PageInfo> query(@RequestBody ReceiveEntity receiveEntity){

        return AxiosResult.success(cartService.queryCart(receiveEntity));
    }
//    @GetMapping("queryByGoodId")
//    public CartItem queryByGoodId(Long id){
//
//        return cartService.queryByGoodId(id);
//
//    }
    @PostMapping("add/{goodId}{userId}")
    public AxiosResult add(Long goodId,Long userId){

        //根据用户ID得到购物车ID，设为购物车项中购物车ID
        Cart cart = cartService.queryByUserId(userId);
        CartItem cartItem=cartService.queryByGoodId(goodId);
        if (cartItem==null){
            CartItem cartItemNew=new CartItem();
            cartItemNew.setCartId(cart.getId());
            cartItemNew.setGoodId(goodId);
            cartItemNew.setGoodNum(1);
            cartItemNew.setItemStatus(0);//0是加入购物车，未下单，1是加入购物车后下单了
            row = cartService.insert(cartItemNew);
        }
        else{
            cartItem.setGoodNum(cartItem.getGoodNum()+1);
            row = cartService.update(cartItem);
        }
        return row>0?AxiosResult.success():AxiosResult.error();
    }
    @PutMapping("modify/{num}{cartItemId}")
    public AxiosResult modify(Integer num,Long cartItemId){

        CartItem cartItem = cartService.findById(cartItemId);
        cartItem.setGoodNum(num);
        row = cartService.update(cartItem);
        return row>0?AxiosResult.success():AxiosResult.error();
    }
    @DeleteMapping("remove/{cartItemIds}")
    public AxiosResult remove(List<Long> cartItemIds){

        Integer row = cartService.deleteBatchIds(cartItemIds);
        return row>0?AxiosResult.success():AxiosResult.error();
    }

    /**
     * 用户前端购物车选定要支付的购物车项，传递过来要支付的购物车项的ID，以集合的形式
     * 支付成功后，后端把这些购物车项的状态改为已成交
     * @param cartItemIds
     * @return
     */
    @GetMapping("pay/{cartItemIds}{userId}")
    public AxiosResult pay(List<Long> cartItemIds,long userId){
        Integer row = cartService.pay(cartItemIds,userId);
        return row>0?AxiosResult.success():AxiosResult.error();
    }
}
