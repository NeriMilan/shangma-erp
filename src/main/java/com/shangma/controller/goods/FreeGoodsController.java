package com.shangma.controller.goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.*;
import com.shangma.service.goods.FreeGoodsService;
import com.shangma.service.goods.GoodsCheckService;
import com.shangma.service.goods.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/20 10:55
 */
@RestController
@RequestMapping("goods/free")
public class FreeGoodsController {
    @Resource
    private FreeGoodsService freeGoodsService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodsCheckService goodsCheckService;

    /**
     * 查询全部
     */
    @GetMapping("list/{pageNum}/{pageSize}")
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FreeGoods> list = freeGoodsService.list();
        PageInfo<FreeGoods> info = new PageInfo<>(list);
        PageBean<FreeGoods> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 赠品数据分页查询
     */
    @GetMapping("find/{pageNum}/{pageSize}")
    public AxiosResult find(@RequestBody FreeGoods freeGoods, @PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FreeGoods> list = freeGoodsService.getAll(freeGoods);
        PageInfo<FreeGoods> info = new PageInfo<>(list);
        PageBean<FreeGoods> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 根据id查询二类商品数据
     */
    @GetMapping("{id}")
    public AxiosResult<Goods> findById(@PathVariable Long id) {
        QueryWrapper<GoodsType> wrapper = new QueryWrapper();
        wrapper.like("id", id).eq("goods_kind", 2);
        return AxiosResult.success(goodsService.getById(id));
    }

    /**
     * 添加赠品数据
     */
    @PostMapping
    public AxiosResult add(@RequestBody FreeGoods freeGoods) {
        boolean add = freeGoodsService.save(freeGoods);
        if (!add) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    /**
     * 修改赠品数据
     */
    @PutMapping()
    public AxiosResult update(@RequestBody FreeGoods freeGoods) {
        boolean update = freeGoodsService.updateById(freeGoods);
        if (!update) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    /**
     * 删除赠品数据
     */
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Long id) {
        boolean delete = freeGoodsService.removeById(id);
        if (!delete) {
            return AxiosResult.error();
        }
        return AxiosResult.success();

    }

    /**
     * 删除赠品数据
     */
    @DeleteMapping("batch/{ids}")
    public AxiosResult batchDeleteByIds(@PathVariable List<Long> ids) {
        boolean delete = freeGoodsService.removeByIds(ids);
        if (!delete) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    /**
     * 赠品转商品
     */
    @PostMapping("/ftog/add")
    public AxiosResult insertGoodsCheck1(@RequestBody GoodsCheck goodsCheck) {
//        boolean insert = freeGoodsService.insertGoodsCheck(goodsCheck);
        goodsCheck.setCheckState(0);
        goodsCheck.setCheckKind(1);
        boolean insert = goodsCheckService.save(goodsCheck);
        if (!insert) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }
    @PostMapping("/ftog/find/{pageNum}/{pageSize}")
    public AxiosResult checkList1(@PathVariable int pageNum, @PathVariable int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<GoodsCheck> wrapper = new QueryWrapper();
        wrapper.eq("check_kind", 1);
        List<GoodsCheck> list = goodsCheckService.list(wrapper);
        PageInfo<GoodsCheck> info = new PageInfo<>(list);
        PageBean<GoodsCheck> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }


//    @PostMapping("/ftog/find/{id}")
//    public AxiosResult<GoodsCheck> findCheck1(@PathVariable Long id) {
//        QueryWrapper<GoodsCheck> wrapper = new QueryWrapper();
//        wrapper.eq("id", id).eq("check_kind", 1);
//        return AxiosResult.success(goodsCheckService.getById(id));
//    }


    @PostMapping("/ftog/checkOK")
    public AxiosResult freeToGoodsCheckOK1(@RequestBody GoodsCheck goodsCheck) {
//        if (goodsCheck.getCheckKind() == 1){
        goodsCheck.setCheckState(1);
        goodsCheckService.updateById(goodsCheck);
        boolean check = freeGoodsService.freeToGoods(goodsCheck.getFreeId(), goodsCheck.getChangeStocks(), goodsCheck.getShopPrice());
        if (!check) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    @PostMapping("/ftog/checkNO")
    public AxiosResult freeToGoodsCheckNO1(@RequestBody GoodsCheck goodsCheck) {
        goodsCheck.setCheckState(2);
        boolean check = goodsCheckService.updateById(goodsCheck);
        if (!check) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    /**
     * 商品转赠品
     */
    @PostMapping("/gtof/add/{changeStocks}")
    public AxiosResult insertGoodsCheck2(@RequestBody Goods goods,@PathVariable Integer changeStocks) {
//        QueryWrapper<FreeGoods> wrapper = new QueryWrapper();
//        wrapper.eq("goods_id", id);
        GoodsCheck goodsCheck = new GoodsCheck();
        if (freeGoodsService.getById(goods.getId()) != null) {
            goodsCheck.setCheckState(0);
            goodsCheck.setCheckKind(2);
            goodsCheck.setChangeStocks(changeStocks);
            boolean insert = goodsCheckService.save(goodsCheck);
            if (!insert) {
                return AxiosResult.error();
            }
            return AxiosResult.success();
        }else {
            FreeGoods freeGoods = new FreeGoods();
            freeGoods.setGoodsPid(goods.getId());
            freeGoods.setGoodsName(goods.getGoodsName());
            freeGoods.setTypeId(goods.getTypeId());
            freeGoods.setBrandId(goods.getBrandId());
            freeGoods.setGoodsModel(goods.getGoodsModel());
            freeGoods.setUnitPrice(goods.getShopPrice());
            freeGoods.setBuyPrice(goods.getBuyPrice());
            freeGoods.setMinStocks(0);
            freeGoods.setGoodsColor(goods.getGoodsColor());
            freeGoodsService.save(freeGoods);
            goodsCheck.setCheckState(0);
            goodsCheck.setCheckKind(2);
            goodsCheck.setChangeStocks(changeStocks);
            goodsCheck.setFreeId(freeGoods.getId());
            boolean insert = goodsCheckService.save(goodsCheck);
            if (!insert) {
                return AxiosResult.error();
            }
            return AxiosResult.success();
        }
    }


    @PostMapping("/gtof/find/{pageNum}/{pageSize}")
    public AxiosResult checkList2(@PathVariable int pageNum, @PathVariable int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<GoodsCheck> wrapper = new QueryWrapper();
        wrapper.eq("check_kind", 2);
        List<GoodsCheck> list = goodsCheckService.list(wrapper);
        PageInfo<GoodsCheck> info = new PageInfo<>(list);
        PageBean<GoodsCheck> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }


    @PostMapping("/gtof/checkOK")
    public AxiosResult freeToGoodsCheckOK2(@RequestBody GoodsCheck goodsCheck) {
        goodsCheck.setCheckState(1);
        goodsCheckService.updateById(goodsCheck);
        Long id = goodsCheck.getFreeId();
        boolean check = freeGoodsService.goodsToFree(id, goodsCheck.getChangeStocks());
        if (!check) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }

    @PostMapping("/gtof/checkNO")
    public AxiosResult freeToGoodsCheckNO2(@RequestBody GoodsCheck goodsCheck) {
        goodsCheck.setCheckState(2);
        boolean check = goodsCheckService.updateById(goodsCheck);
        if (!check) {
            return AxiosResult.error();
        }
        return AxiosResult.success();
    }




}
