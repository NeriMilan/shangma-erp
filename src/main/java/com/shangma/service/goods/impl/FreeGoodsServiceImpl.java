package com.shangma.service.goods.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.goods.FreeGoods;
import com.shangma.entity.goods.Goods;
import com.shangma.mapper.goods.FreeGoodsMapper;
import com.shangma.service.goods.FreeGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/20 10:53
 */
@Service
public class FreeGoodsServiceImpl extends ServiceImpl<FreeGoodsMapper, FreeGoods> implements FreeGoodsService {
    @Resource
    private FreeGoodsMapper freeGoodsMapper;


    @Override
    public List getAll(FreeGoods freeGoods) {
        List<Goods> list = freeGoodsMapper.getFreeGoodsList(freeGoods);
        return list;
    }

//    @Override
//    public boolean insertGoodsCheck(GoodsCheck goodsCheck) {
//        return freeGoodsMapper.insertGoodsCheck(goodsCheck) == 1 ? true : false;
//        return goodsCheckMapper.insert(goodsCheck) == 1 ? true : false;
//    }
//
//    @Override
//    public boolean freeToGoodsState() {
//        return true;
//    }

    @Override
    public boolean freeToGoods(Long id, Integer addStocks, Double shopPrice) {
        return freeGoodsMapper.freeToGoods(id, addStocks, shopPrice) == 0 ? false : true;
    }



    @Override
    public boolean goodsToFree(Long id, Integer addStocks) {
        return freeGoodsMapper.goodsToFree(id,addStocks)  == 1 ? false : true;
    }
}
