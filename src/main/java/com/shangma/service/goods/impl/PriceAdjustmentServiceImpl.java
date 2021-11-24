package com.shangma.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.PriceAdjustment;
import com.shangma.mapper.goods.PriceAdjustmentMapper;
import com.shangma.service.goods.PriceAdjustmentService;
import com.shangma.service.goods.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAdjustmentServiceImpl extends BaseServiceImpl<PriceAdjustment> implements PriceAdjustmentService {

    @Autowired
    private PriceAdjustmentMapper mapper;

    @Override
    public PageBean<PriceAdjustment> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PriceAdjustment> priceAdjustments = mapper.selectList(null);
        long total = new PageInfo<>(priceAdjustments).getTotal();
        return PageBean.initData(total, priceAdjustments);
    }

    /**
     *出现的错误：mapper层用的list而不是PageBean
     */
    @Override
    public PageBean<PriceAdjustment> search(PriceAdjustment priceAdjustment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PriceAdjustment> page = mapper.search(priceAdjustment);
        PageInfo<PriceAdjustment> tPageInfo = new PageInfo<>(page);
        long total = tPageInfo.getTotal();
        return PageBean.initData(total,page);
    }

    /**
     *
     * @return 最后一条数据id值
     */
    @Override
    public Long lastId() {
        Long aLong = mapper.lastId();
        System.out.println(aLong);
        return  aLong;
    }
}
