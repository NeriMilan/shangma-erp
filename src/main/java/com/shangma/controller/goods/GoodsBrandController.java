package com.shangma.controller.goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.controller.base.BaseController;
import com.shangma.entity.goods.FreeGoods;
import com.shangma.entity.goods.Goods;
import com.shangma.entity.goods.GoodsBrand;
import com.shangma.entity.goods.GoodsType;
import com.shangma.service.goods.GoodsBrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/17 16:12
 */
@RestController
@RequestMapping("goods/brand")
public class GoodsBrandController extends BaseController {
    @Resource
    private GoodsBrandService goodsBrandService;

    /**
     * 查询所有
     */

    @GetMapping("list/{pageNum}/{pageSize}")
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsBrand> list = goodsBrandService.list();
        PageInfo<GoodsBrand> info = new PageInfo<>(list);
        PageBean<GoodsBrand> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 根据名字查询
     */
    @GetMapping("/{pageNum}/{pageSize}/{brandName}")
    public AxiosResult find(@PathVariable int pageNum, @PathVariable int pageSize, @PathVariable String brandName) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<GoodsBrand> wrapper = new QueryWrapper();
        wrapper.like("brand_name", brandName);
        List<GoodsBrand> types = goodsBrandService.list(wrapper);
        PageInfo<GoodsBrand> info = new PageInfo<>(types);
        PageBean<GoodsBrand> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 添加品牌数据
     */
    @PostMapping
    public AxiosResult add(@RequestBody GoodsBrand goodsBrand){
        boolean add = goodsBrandService.save(goodsBrand);
        if (!add){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }


    /**
     * 修改品牌数据
     */
    @PutMapping
    public AxiosResult update(@RequestBody GoodsBrand goodsBrand){
        boolean update = goodsBrandService.updateById(goodsBrand);
        if (!update){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    /**
     * 删除品牌数据
     */
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Long id){
        boolean delete = goodsBrandService.removeById(id);
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
        boolean delete = goodsBrandService.removeByIds(ids);
        if (!delete){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }


}
