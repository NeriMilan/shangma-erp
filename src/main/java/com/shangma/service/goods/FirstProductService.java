package com.shangma.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;

import com.shangma.entity.goods.FirstProduct;


import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/18 16:10
 */
public interface FirstProductService extends IService<FirstProduct> {
    /**
     * 多表分页查询
     */

    List<FirstProduct> getAll(FirstProduct firstProduct);
}
