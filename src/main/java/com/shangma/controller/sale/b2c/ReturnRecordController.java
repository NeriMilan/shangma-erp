package com.shangma.controller.sale.b2c;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.ReturnEntityRecord;
import com.shangma.service.sale.b2c.ReturnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateTime: 2021/11/24  17:06
 */
@RestController
@RequestMapping("returnRecord")
public class ReturnRecordController {
    @Autowired
    private ReturnRecordService returnRecordService;

    @GetMapping("findAll")
    public AxiosResult findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                               @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = returnRecordService.findAllOrder(currentPage, pageSize);
        return AxiosResult.success(pageBean);
    }

    @GetMapping("search")
    public AxiosResult search(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              ReturnEntityRecord returnEntityRecord){
        PageBean pageBean = returnRecordService.findBySearch(currentPage, pageSize, returnEntityRecord);
        return AxiosResult.success(pageBean);
    }

    @GetMapping("getOrderInfo")
    public AxiosResult getOrderInfo(Long orderId){
        ReturnEntityRecord entityRecord = returnRecordService.getOrderInfo(orderId);
        return AxiosResult.success(entityRecord);
    }
}
