package com.shangma.controller.goods;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.controller.base.BaseController;
import com.shangma.entity.goods.FirstProduct;
import com.shangma.service.goods.FirstProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/18 16:21
 */
@RestController
@RequestMapping("goods/product")
public class FirstProductController extends BaseController {

    @Autowired
    public FirstProductService firstProductService;
    /**
     * 查询全部
     */
    @GetMapping("list/{pageNum}/{pageSize}")
    public AxiosResult list(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FirstProduct> list = firstProductService.list();
        PageInfo<FirstProduct> info = new PageInfo<>(list);
        PageBean<FirstProduct> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }

    /**
     * 产品查询
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public AxiosResult getAll(@RequestBody FirstProduct firstProduct, @PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FirstProduct> list = firstProductService.getAll(firstProduct);
        PageInfo<FirstProduct> info = new PageInfo<>(list);
        PageBean<FirstProduct> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }
}