package com.shangma.mapper.goods;

import com.shangma.entity.goods.PriceAdjustment;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface PriceAdjustmentMapper extends MyMapper<PriceAdjustment> {
   List<PriceAdjustment> search(PriceAdjustment priceAdjustment);

   Long lastId();
}
