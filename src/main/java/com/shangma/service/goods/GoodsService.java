package com.shangma.service.goods;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shangma.entity.goods.Goods;


import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/17 11:04
 */
public interface GoodsService extends IService<Goods> {
    /**
     * 多表分页查询
     */

    List getAll(Goods goods);

}
