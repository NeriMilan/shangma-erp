package com.shangma.controller.goods;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.common.utils.Utils;
import com.shangma.entity.goods.ActivityMarket;
import com.shangma.service.goods.ActivityMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("Market")
public class ActivityMarketController {
    @Autowired
    private ActivityMarketService marketService;
    
    /**
     * 查询所有加分页
     */
    @GetMapping("find/{pageNumber}/{pageSize}")
    public AxiosResult<PageBean<ActivityMarket>> list(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        PageBean<ActivityMarket> list = marketService.list(pageNumber, pageSize);
        return AxiosResult.success(list);
    }
    
    /**
     * 模糊查询加分页
     */
    @PostMapping("search")
    public AxiosResult<PageBean<ActivityMarket>> search(@RequestBody ActivityMarket market, @RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNum") Integer pageNumber) {
        
        PageBean<ActivityMarket> search = marketService.search(market, pageNumber, pageSize);
        return AxiosResult.success(search);
    }
    
    @GetMapping("find/{id}")
    public AxiosResult<ActivityMarket> findById(@PathVariable Long id) {
        ActivityMarket market = marketService.findById(id);
        return AxiosResult.success(market);
    }
    
    @PostMapping
    public AxiosResult add(@RequestBody ActivityMarket activityMarket) {
        
        int row = marketService.add(activityMarket);
        return Utils.Judgement(row);
    }
    
    @PutMapping
    public AxiosResult update(@RequestBody ActivityMarket activityMarket) {
        int row = marketService.update(activityMarket);
        return Utils.Judgement(row);
    }
    
    @DeleteMapping("{id}")
    public AxiosResult delete(@PathVariable Long id) {
        int row = marketService.deleteById(id);
        return Utils.Judgement(row);
    }
    
    @DeleteMapping("batch/{ids}")
    public AxiosResult batchDeleteByIds(@PathVariable List<Long> ids) {
        int row = marketService.batchDeleteByIds(ids);
        return Utils.Judgement(row);
    }
    
    @PostMapping("review")
    public AxiosResult review(@RequestBody ActivityMarket market) {
        int row = marketService.review(market);
        return Utils.Judgement(row);
    }
    @PostMapping("winPeople")
    public AxiosResult winPeople(@RequestBody ActivityMarket market) {
        int row = marketService.winPeople(market);
        return Utils.Judgement(row);
    }
}
