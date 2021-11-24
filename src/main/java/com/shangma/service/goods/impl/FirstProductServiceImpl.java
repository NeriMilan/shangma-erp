package com.shangma.service.goods.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.entity.goods.FirstProduct;
import com.shangma.mapper.goods.FirstProductMapper;
import com.shangma.service.goods.FirstProductService;
import com.shangma.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/18 16:10
 */
@Service
public class FirstProductServiceImpl  extends ServiceImpl<FirstProductMapper,FirstProduct> implements FirstProductService{
    @Resource
    private FirstProductMapper firstProductMapper;

    @Override
    public List<FirstProduct> getAll(FirstProduct firstProduct) {
            List<FirstProduct> list = firstProductMapper.getProductList(firstProduct);
            return list;
    }
}
