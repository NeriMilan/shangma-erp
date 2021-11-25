package com.shangma.controller.goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.Goods;
import com.shangma.entity.goods.GoodsType;
import com.shangma.service.goods.GoodsTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/19 13:56
 */
@RestController
@RequestMapping("goods/type")
public class GoodsTypeController {
    @Resource
    private GoodsTypeService goodsTypeService;
    /**
     * 查询全部
     */
    @GetMapping("list/{pageNum}/{pageSize}")
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsType> list = goodsTypeService.list();
        PageInfo<GoodsType> info = new PageInfo<>(list);
        PageBean<GoodsType> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 根据名字查询
     */
    @GetMapping("/find/{pageNum}/{pageSize}/{typeName}")
    public AxiosResult find(@PathVariable int pageNum, @PathVariable int pageSize, @PathVariable String typeName) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<GoodsType> wrapper = new QueryWrapper();
        wrapper.like("type_name", typeName);
        List<GoodsType> types = goodsTypeService.list(wrapper);
        PageInfo<GoodsType> info = new PageInfo<>(types);
        PageBean<GoodsType> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 添加种类数据
     */
    @PostMapping
    public AxiosResult add(@RequestBody GoodsType goodsType) {
        boolean add = goodsTypeService.save(goodsType);
        if (!add){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }


    /**
     * 修改种类数据
     */
    @PutMapping
    public AxiosResult update(@RequestBody GoodsType goodsType) {
        boolean update = goodsTypeService.updateById(goodsType);
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
        boolean delete = goodsTypeService.removeById(id);
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
        boolean delete = goodsTypeService.removeByIds(ids);
        if (!delete){
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }
}
