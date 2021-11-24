package com.shangma.service.sale.cart.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.entity.goods.Goods;
import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.cart.Cart;
import com.shangma.entity.sale.cart.CartItem;
import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.mapper.goods.GoodsMapper;
import com.shangma.mapper.sale.b2c.OnlineOrderMapper;
import com.shangma.mapper.sale.b2c.OrderGoodsMapper;
import com.shangma.mapper.sale.b2c.OrderMapper;
import com.shangma.mapper.sale.cart.CartMapper;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import com.shangma.service.sale.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName:CartServiceImpl
 * @Description: TODO
 * @Author:
 * @Date:2021/11/18 15:32
 * @Version: JDK1.8
 */
@Service
public class CartServiceImpl extends BaseServiceImpl<CartItem> implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OnlineOrderMapper onlineOrderMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public CartItem queryByGoodId(long id) {

        return cartMapper.selectByGoodId(id);
    }

    @Override
    public PageInfo<CartItem> queryCart(ReceiveEntity receiveEntity) {

        PageHelper.startPage(receiveEntity.getPageNum(),receiveEntity.getPageSize());
        List<CartItem> cartItems = cartMapper.selectCart(receiveEntity);
        PageInfo<CartItem> cartItemPageInfo = new PageInfo<>(cartItems);
        return cartItemPageInfo;
    }

    @Override
    public Cart queryByUserId(long id) {
        return cartMapper.selectByUserId(id);
    }

    /**
     * 通过提交的订单项ID把数据库中订单项状态改为已成交，并且生成订单插入订单表中
     * 生成订单商品对应信息插入订单商品表中
     * @param cartItemIds
     * @return
     */
    @Override
    @Transactional
    public Integer pay(List<Long> cartItemIds,long userId) {

        CartItem cartItem = new CartItem();
        cartItem.setItemStatus(1);
        int update= cartMapper.update(cartItem, new LambdaUpdateWrapper<CartItem>().in(CartItem::getId, cartItemIds));
        InternetSaleOrder internetSaleOrder = new InternetSaleOrder();
        internetSaleOrder.setOrderType("网店订单");
        internetSaleOrder.setOrderAction("购买");
        internetSaleOrder.setBusinessType("普通类型");
        internetSaleOrder.setPayment("先收款再发货");
        internetSaleOrder.setDistributionMode("中通快递");
        internetSaleOrder.setOrderStatus("已收款未发货");
        internetSaleOrder.setCustomerId(userId);
        internetSaleOrder.setExportStatus('0');
        internetSaleOrder.setShopType("淘宝网店");
        internetSaleOrder.setOrderDate(LocalDateTime.now());
        onlineOrderMapper.insert(internetSaleOrder);
        Long orderId = internetSaleOrder.getOrderId();
        OrderGoods orderGoods=new OrderGoods();
        List<CartItem> cartItems = cartMapper.selectBatchIds(cartItemIds);
        cartItems.forEach((cartItem1) ->{
            orderGoods.setOrderId(orderId);
            orderGoods.setGoodId(cartItem1.getGoodId());
            Goods goods = goodsMapper.selectById(cartItem1.getGoodId());
            orderGoods.setGoodName(goods.getGoodsName());
            orderGoods.setGoodPrice(goods.getMarketPrice());
            orderGoods.setGoodCount((long)cartItem1.getGoodNum());
            orderGoods.setTotalAmount(orderGoods.getGoodPrice()*orderGoods.getGoodCount());
            orderGoodsMapper.insert(orderGoods);
        });
        return update;
    }
}
