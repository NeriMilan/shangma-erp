package com.shangma.controller.goods;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.FirstProduct;
import com.shangma.entity.goods.Goods;
import com.shangma.service.goods.FirstProductService;
import com.shangma.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/18 22:09
 */
@RestController
@RequestMapping("goods/goods")
public class GoodsController  {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FirstProductService productService;

    /**
     * 查询全部
     */
    @GetMapping("list/{pageNum}/{pageSize}")
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsService.list();
        PageInfo<Goods> info = new PageInfo<>(list);
        PageBean<Goods> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 一类商品分页查询
     */
    @PostMapping ("/find/{pageNum}/{pageSize}")
    public AxiosResult find(@RequestBody Goods goods, @PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsService.getAll(goods);
        PageInfo<Goods> info = new PageInfo<>(list);
        PageBean<Goods> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 根据id查询产品数据
     */
    @GetMapping("{id}")
    public AxiosResult<FirstProduct> findById(@PathVariable Long id){
        return AxiosResult.success(productService.getById(id));
    }
    /**
     * 添加商品数据
     */
    @PostMapping
    public AxiosResult add(@RequestBody Goods goods){
        boolean add = goodsService.save(goods);
        if (!add){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }


    /**
     * 修改商品数据
     */
    @PutMapping()
    public AxiosResult update(@RequestBody Goods goods){
        boolean update = goodsService.updateById(goods);
        if (!update){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    /**
     * 删除商品数据
     */
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Long id){
        boolean delete = goodsService.removeById(id);
        if (!delete){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }
    /**
     * 删除多条数据
     */
    @DeleteMapping("batch/{ids}")
    public AxiosResult batchDeleteByIds(@PathVariable List<Long> ids){
        boolean delete = goodsService.removeByIds(ids);
        if (!delete){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }
}
