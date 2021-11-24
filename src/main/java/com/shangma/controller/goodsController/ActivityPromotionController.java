package com.shangma.controller.goodsController;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.common.utils.Utils;
import com.shangma.entity.goodsEntity.ActivityPromotion;
import com.shangma.service.goodsService.ActivityPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityPromotionController {
    @Autowired
    private ActivityPromotionService PromotionService;

    /**
     * 查询所有且分页 ("{pageSize/pageNumber}")
     */
    @GetMapping("/find/{pageSize}/{pageNumber}")
    public AxiosResult<PageBean<ActivityPromotion>> list(@PathVariable Integer pageSize, @PathVariable Integer pageNumber) {
        PageBean<ActivityPromotion> list = PromotionService.list(pageSize, pageNumber);

        return AxiosResult.success(list);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/find/{id}")
    public AxiosResult<ActivityPromotion> findById(@PathVariable Long id) {
        return AxiosResult.success(PromotionService.findById(id));
    }

    /**
     * 模糊查询加分页
     */
    @PostMapping("/search")
    public AxiosResult<PageBean<ActivityPromotion>> search(@RequestBody ActivityPromotion activityPromotion, @RequestParam(value ="pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        System.out.println("1111"+activityPromotion);
        PageBean<ActivityPromotion> activityPromotionPageBean = PromotionService.search(activityPromotion, pageSize, pageNumber);
        return AxiosResult.success(activityPromotionPageBean);
    }

    /**
     * 增加活动推广
     */
    @PostMapping
    public AxiosResult increase(@RequestBody ActivityPromotion activityPromotion) {
        int row = PromotionService.increase(activityPromotion);
        return Utils.Judgement(row);
    }

    /**
     *修改活动推广
     */
    @PutMapping
    public AxiosResult update(@RequestBody ActivityPromotion activityPromotion){
        int row = PromotionService.update(activityPromotion);
        return Utils.Judgement(row);
    }

    /**
     *单条数据删除
     */
    @DeleteMapping
    public AxiosResult delete(@PathVariable Long id){
        int row = PromotionService.deleteById(id);
        return Utils.Judgement(row);
    }

    /**
     *多条数据删除
     */
    @DeleteMapping("batch/{ids}")
    public AxiosResult batchDeleteByIds(@PathVariable List<Long>ids){
        int row = PromotionService.batchDeleteByIds(ids);
        return Utils.Judgement(row);
    }
    /**
     * 审核
     */
    @PostMapping("/review")
    public AxiosResult review(@RequestBody ActivityPromotion activityPromotion){
        int row = PromotionService.review(activityPromotion);
        return Utils.Judgement(row);
    }
}
