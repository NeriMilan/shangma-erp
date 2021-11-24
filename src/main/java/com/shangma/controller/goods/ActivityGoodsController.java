package com.shangma.controller.goods;


import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.goods.ActivityGoods;
import com.shangma.service.goods.ActivityGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("activitygoods")
public class ActivityGoodsController {
    @Autowired
    private ActivityGoodsService activityGoodsService;

    @GetMapping("find/{pageNumber}/{pageSize}")
    public AxiosResult<PageBean<ActivityGoods>> list(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        PageBean<ActivityGoods> list = activityGoodsService.list(pageNumber, pageSize);
        return AxiosResult.success(list);
    }

    @GetMapping("find/{id}")
    public AxiosResult findById(@PathVariable Long id) {
        ActivityGoods goods = activityGoodsService.findById(id);
        return AxiosResult.success(goods);
    }

    @PostMapping("search")
    public AxiosResult<PageBean<ActivityGoods>> search(@RequestBody ActivityGoods activityGoods, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize) {
        PageBean<ActivityGoods> search = activityGoodsService.search(activityGoods, pageNumber, pageSize);
        return AxiosResult.success(search);
    }
}
