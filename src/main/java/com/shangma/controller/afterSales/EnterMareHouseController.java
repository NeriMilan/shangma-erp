package com.shangma.controller.afterSales;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.afterSales.EnterMareHouse;
import com.shangma.service.afterSales.EnterMareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("enterMareHouse")
public class EnterMareHouseController {
    @Autowired
    private EnterMareHouseService enterMareHouseService;
    /**
     * 动态查询&分页
     *
     */
    @GetMapping("select")
    public AxiosResult selectBySearch(
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            EnterMareHouse enterMareHouse){
        PageHelper.startPage(pageIndex,pageSize);
        List<EnterMareHouse> enterMareHouses = enterMareHouseService.selectBySearch(enterMareHouse);
        PageInfo<EnterMareHouse> pageInfo = new PageInfo<>(enterMareHouses);
        long total = pageInfo.getTotal();
        List<EnterMareHouse> list = pageInfo.getList();
        PageBean<EnterMareHouse> data = PageBean.initData(total, list);
        if (data!=null){
            return AxiosResult.success(data);
        }

        return AxiosResult.error();
    }


}
