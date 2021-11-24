package com.shangma.service.goods.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.goods.Goods;
import com.shangma.entity.system.Role;
import com.shangma.entity.system.User;
import com.shangma.mapper.goods.GoodsMapper;

import com.shangma.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/17 16:04
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,Goods> implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public List getAll(Goods goods) {
        List<Goods> list = goodsMapper.getGoodsList(goods);
        return list;
    }
    public Map<String, Object> loadUserInfo(HttpSession session) {
        Map<String,Object> map = new HashMap<>();

        User user = (User)session.getAttribute("user");

        map.put("userId", user.getId());

        return map;
    }
    public Integer reduce(Integer num, Long id) {
        return goodsMapper.reduce(num, id);
    }
    
    
    //加上商品的数量
    public Integer plus(Integer num,Long id){
        return  goodsMapper.plus(num, id);
    };
}
