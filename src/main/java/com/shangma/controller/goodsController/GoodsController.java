package com.shangma.controller.goodsController;


import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.common.utils.Utils;
import com.shangma.entity.goodsEntity.Goods;
import com.shangma.service.goodsService.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询所有商品
     */
    @GetMapping("/find/{pageNumber}/{pageSize}")
    public AxiosResult<PageBean<Goods>> find(@PathVariable Integer pageSize,@PathVariable Integer pageNumber) {
        PageBean<Goods> list = goodsService.list(pageSize,pageNumber);
        return AxiosResult.success(list);
    }

    /**
     * 通过条件查询的商品
     */
    @PostMapping("/search")
    public AxiosResult<PageBean<Goods>> search(@RequestBody Goods goods, @RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {

        PageBean<Goods> list = goodsService.search(goods, pageSize, pageNumber);
        return AxiosResult.success(list);
    }

    /**
     * 根据id查询
     */

    @GetMapping("/find/{id}")
    public AxiosResult<Goods> findById(@PathVariable Long id){
        Goods goods = goodsService.findById(id);
        return AxiosResult.success(goods);
    }

    /**
     * 添加商品
     */
    @PostMapping
    public AxiosResult add(@RequestBody Goods goods) {
        int row = goodsService.add(goods);
       return Utils.Judgement(row);
    }

    /**
     * 修改商品
     */
    @PutMapping
    public AxiosResult update(@RequestBody Goods goods) {
        int row = goodsService.update(goods);
        return Utils.Judgement(row);
    }

    /**
     *通过id删除
     */
    @DeleteMapping("{id}")
    public AxiosResult delete(@PathVariable Long id){
        System.out.println(id);
        int row = goodsService.deleteById(id);
        return Utils.Judgement(row);
    }
    /**
     * 批量删除
     */
    @DeleteMapping("batch/{ids}")
    public AxiosResult batchDeleteByIds(@PathVariable List<Long> ids){
        int row = goodsService.batchDeleteByIds(ids);
        return Utils.Judgement(row);
    }
}
