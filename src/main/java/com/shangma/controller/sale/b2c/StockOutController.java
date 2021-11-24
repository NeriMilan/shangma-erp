package com.shangma.controller.sale.b2c;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.StockOutManage;
import com.shangma.entity.system.User;
import com.shangma.service.sale.b2c.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @CreateTime: 2021/11/23  14:36
 */
@RestController
@RequestMapping("stockOut")
public class StockOutController {
    @Autowired
    private StockOutService stockOutService;
    @GetMapping("findAll")
    public AxiosResult findAll(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = stockOutService.findAllStockOut(currentPage, pageSize);
        return AxiosResult.success(pageBean);
    }
    @GetMapping("search")
    public AxiosResult search(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              StockOutManage stockOutManage){
        PageBean search = stockOutService.search(stockOutManage, currentPage, pageSize);
        return AxiosResult.success(search);
    }
    @GetMapping("getOrderInfo")
    public AxiosResult getOrderInfo(Long orderId, HttpServletRequest request){
        request.getSession().getAttribute("user");
        StockOutManage orderInfo = stockOutService.getOrderInfo(orderId);
        return AxiosResult.success(orderInfo);
    }
    @PutMapping("exportRecord")
    public AxiosResult exportRecord(HttpServletRequest request,@RequestBody Long[] orderIds){
        String userName = "";
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null){
            userName = "未知";
        }else {
            userName = user.getUsername();
        }
        List<Long> longList = Arrays.asList(orderIds);
        stockOutService.generateRecord(longList,userName);
        return AxiosResult.success();
    }
}
