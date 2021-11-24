package com.shangma.controller.sale.b2c;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.StockOutRecord;
import com.shangma.service.sale.b2c.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateTime: 2021/11/23  19:26
 */
@RestController
@RequestMapping("stockRecord")
public class StockRecordController {
    @Autowired
    private StockRecordService stockRecordService;
    @GetMapping("findAll")
    public AxiosResult findAll(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = stockRecordService.findAllRecord(currentPage, pageSize);
        return AxiosResult.success(pageBean);
    }
    @GetMapping("search")
    public AxiosResult search(StockOutRecord stockOutRecord,
                              @RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = stockRecordService.findBySearch(stockOutRecord, currentPage, pageSize);
        return AxiosResult.success(pageBean);
    }
    @GetMapping("getOrderInfo")
    public AxiosResult getOrderInfo(Long orderId){
        StockOutRecord stockOutRecord = stockRecordService.getOrderInfo(orderId);
        return AxiosResult.success(stockOutRecord);
    }
}
