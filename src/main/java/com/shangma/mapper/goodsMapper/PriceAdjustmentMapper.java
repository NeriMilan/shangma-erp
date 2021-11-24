package com.shangma.mapper.goodsMapper;

import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goodsEntity.PriceAdjustment;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface PriceAdjustmentMapper extends MyMapper<PriceAdjustment> {
   List<PriceAdjustment> search(PriceAdjustment priceAdjustment);

   Long lastId();
}
