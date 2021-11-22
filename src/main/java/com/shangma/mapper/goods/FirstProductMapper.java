package com.shangma.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.goods.FirstProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FirstProductMapper extends BaseMapper<FirstProduct> {
    /**
     * 多表分页查询
     */
    List<FirstProduct> getProductList(@Param("goodsModel") String goodsModel,
                                      @Param("goodsColor") String goodsColor,
                                      @Param("brandId") Long brandId,
                                      @Param("typeId") Long typeId);
}