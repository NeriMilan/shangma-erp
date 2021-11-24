package com.shangma.controller.sale.b2c;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.InternetSaleOrder;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.service.sale.b2c.OnlineOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @CreateTime: 2021/11/22  11:04
 */
@RestController
@RequestMapping("onlineOrder")
public class OnlineOrderController {
    /**
     * 1.分页查询网店订单信息
     * 2.根据模糊查询条件和分页条件，展示网店订单信息
     * 3.将网店订单导入到总订单数据库中
     *      接收网店类型和是否已导出的状态，返回符合条件的订单数据
     *      接收前端选中的网店订单编号，修改是否已导出的状态，并将网店订单信息导出到总订单数据库中
     */
    private static final Logger logger = LoggerFactory.getLogger(OnlineOrderController.class);
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
        for (int i = 0; i < list.size(); i++) {
            String s = "";
            List<OrderGoods> goods = onlineOrderService.findGoods(list.get(i).getOrderId());
            for (int j = 0; j < goods.size() ; j++){
                s += goods.get(j).getGoodName()+";";
            }
            logger.debug(s);
            list.get(i).setGoodsNames(s);
        }
        long total = pageInfo.getTotal();
        PageBean<InternetSaleOrder> pageBean = PageBean.initData(total, list);
        return AxiosResult.success(pageBean);
    }
    @GetMapping("search")
    public AxiosResult search(InternetSaleOrder internetSaleOrder,
                              @RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = onlineOrderService.search(internetSaleOrder, currentPage, pageSize);
        return AxiosResult.success(pageBean);
    }
    @GetMapping("getOrderInfo")
    public AxiosResult getOrderInfo(Long orderId){
        InternetSaleOrder saleOrder = onlineOrderService.findInfoByOrderId(orderId);
        return AxiosResult.success(saleOrder);
    }
    @GetMapping("getOrderToExported")
    public AxiosResult getOrderToExported(InternetSaleOrder internetSaleOrder,
                                          @RequestParam(defaultValue = "1") Integer currentPage,
                                          @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = onlineOrderService.searchByStatus(internetSaleOrder, currentPage, pageSize);
        return AxiosResult.success(pageBean);
    }
    @PutMapping("doExportOrder")
    public AxiosResult doExportOrder(@RequestBody Long[] orderIds){
        List<Long> idList = Arrays.asList(orderIds);
        onlineOrderService.exportOrder(idList);
        return AxiosResult.success();
    }
}
