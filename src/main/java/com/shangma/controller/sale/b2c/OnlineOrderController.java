package com.shangma.controller.sale.b2c;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.service.sale.b2c.OnlineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CreateTime: 2021/11/22  11:04
 */
@RestController
@RequestMapping("OnlineOrder")
public class OnlineOrderController {
    /**
     * 1.分页查询网店订单信息
     * 2.根据模糊查询条件和分页条件，展示网店订单信息
     * 3.将网店订单导入到总订单数据库中
     *      接收网店类型和是否已导出的状态，返回符合条件的订单数据
     *      接收前端选中的网店订单编号，修改是否已导出的状态，并将网店订单信息导出到总订单数据库中
     */
    @Autowired
    private OnlineOrderService onlineOrderService;
    @GetMapping("findAll")
    public AxiosResult<PageBean<InternetSaleOrder>> findAll(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "5") Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<InternetSaleOrder> orderList = onlineOrderService.findAll();
        PageInfo<InternetSaleOrder> pageInfo = PageInfo.of(orderList);
        List<InternetSaleOrder> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        PageBean<InternetSaleOrder> pageBean = PageBean.initData(total, list);
        return AxiosResult.success(pageBean);
    }
}
