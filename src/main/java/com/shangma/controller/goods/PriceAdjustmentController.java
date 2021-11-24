package com.shangma.controller.goods;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.common.utils.Utils;
import com.shangma.entity.goods.PriceAdjustment;
import com.shangma.service.goods.PriceAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("price")
public class PriceAdjustmentController {
    @Autowired
    private PriceAdjustmentService adjustmentService;

    @GetMapping("find/{pageNumber}/{pageSize}")
    public AxiosResult<PageBean<PriceAdjustment>> list(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        PageBean<PriceAdjustment> list = adjustmentService.list(pageNumber, pageSize);
        return AxiosResult.success(list);
    }

    @GetMapping("find/{id}")
    public AxiosResult findByid(@PathVariable Long id) {
        PriceAdjustment adjustment = adjustmentService.findById(id);
        return AxiosResult.success(adjustment);
    }

    /**
     *模糊查询
     */
    @PostMapping("search")
    public AxiosResult search(@RequestBody PriceAdjustment adjustment, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize) {
        PageBean<PriceAdjustment> search = adjustmentService.search(adjustment, pageNumber, pageSize);
        return AxiosResult.success(search);
    }

    @PostMapping
    public AxiosResult add(@RequestBody PriceAdjustment adjustment) {
        int row = adjustmentService.add(adjustment);
        return Utils.Judgement(row);
    }

    @PutMapping
    public AxiosResult update(@RequestBody PriceAdjustment adjustment) {
        int row = adjustmentService.update(adjustment);
        return Utils.Judgement(row);
    }

    @DeleteMapping("{id}")
    public AxiosResult delete(@PathVariable Long id){
        int row = adjustmentService.deleteById(id);
        return Utils.Judgement(row);
    }
    @DeleteMapping("batch/{ids}")
    public AxiosResult batchDeleteByIds(@PathVariable List<Long> ids){
        int row = adjustmentService.batchDeleteByIds(ids);
        return Utils.Judgement(row);
    }
}
