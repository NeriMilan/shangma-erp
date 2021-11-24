package com.shangma.service.goods.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.goods.GoodsType;
import com.shangma.mapper.goods.GoodsTypeMapper;
import com.shangma.service.goods.GoodsTypeService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/19 13:54
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements GoodsTypeService {
}
